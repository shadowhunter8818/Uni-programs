/**
 * @file offb_node.cpp
 * @brief offboard example node, written with mavros version 0.14.2, px4 flight
 * stack and tested in Gazebo SITL
 */

#include <ros/ros.h>
#include <geometry_msgs/PoseStamped.h>
#include <geometry_msgs/TransformStamped.h>
#include <geometry_msgs/Point.h>
#include <mavros_msgs/CommandBool.h>
#include <mavros_msgs/SetMode.h>
#include <mavros_msgs/State.h>

#include <tf/transform_datatypes.h>
#include "geometry_msgs/Vector3.h"
#include "geometry_msgs/Quaternion.h"

#include <vector>
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <math.h>

//rotation setup
geometry_msgs::Vector3 toEuler(geometry_msgs::Quaternion q){
    geometry_msgs::Vector3 e;

    double q2sqr = q.y * q.y;
    double t0 = -2.0 * (q2sqr + q.z * q.z) + 1.0;
    double t1 = +2.0 * (q.x * q.y + q.w * q.z);
    double t2 = -2.0 * (q.x * q.z - q.w * q.y);
    double t3 = +2.0 * (q.y * q.z + q.w * q.x);
    double t4 = -2.0 * (q.x * q.x + q2sqr) + 1.0;

    t2 = t2 > 1.0 ? 1.0 : t2;
    t2 = t2 < -1.0 ? -1.0 : t2;

    e.x = atan2(t3, t4);
    e.y = asin(t2);
    e.z = atan2(t1, t0);

    return e;
}

geometry_msgs::Quaternion toQuaternion(geometry_msgs::Vector3 e){
    geometry_msgs::Quaternion q;

    double t0 = cos(e.z * 0.5);
    double t1 = sin(e.z * 0.5);
    double t2 = cos(e.x * 0.5);
    double t3 = sin(e.x * 0.5);
    double t4 = cos(e.y * 0.5);
    double t5 = sin(e.y * 0.5);

    q.w = t2 * t4 * t0 + t3 * t5 * t1;
    q.x = t3 * t4 * t0 - t2 * t5 * t1;
    q.y = t2 * t5 * t0 + t3 * t4 * t1;
    q.z = t2 * t4 * t1 - t3 * t5 * t0;

    return q;
}
//end rotation setup

mavros_msgs::State current_state;
geometry_msgs::PoseStamped goal_position;
geometry_msgs::Vector3 rotate;

//Global messages

std::vector<geometry_msgs::Point> waypoints;
int waypoint_counter = 0;
int turns_done = 0;
int turn_check = 1;

//Target processing global messages
int target_found = 0;
int target_check = 0;
ros::Time previous_pause = ros::Time::now();

// end target processsing global messages


void state_cb(const mavros_msgs::State::ConstPtr& msg){
    current_state = *msg;
}

void positionCallback(const geometry_msgs::TransformStamped msg){
int turns_todo[3] = {4,8,13};

//Target positioning pause code

if (target_found != target_check)
{
ros::Time previous_pause = ros::Time::now();

		goal_position.pose.position.x = msg.transform.translation.x;
		goal_position.pose.position.y = msg.transform.translation.y;
		goal_position.pose.position.z = 1;
}

if (ros::Time::now() - previous_pause > ros::Duration(11.0))
{
if (waypoint_counter != 0)
{
		goal_position.pose.position.x = waypoints.at(waypoint_counter).x;
		goal_position.pose.position.y = waypoints.at(waypoint_counter).y;
		goal_position.pose.position.z = waypoints.at(waypoint_counter).z;
}
//End pause code

if ((waypoint_counter == turns_todo[turns_done]) && (turn_check != 0) && (fabs(goal_position.pose.position.x - msg.transform.translation.x) < 0.1 ) && (fabs(goal_position.pose.position.y - msg.transform.translation.y) < 0.1))
{
rotate.z = -1.57*(1+turns_done); //90 Deg.
goal_position.pose.orientation = toQuaternion(rotate);
turn_check=0;
turns_done++;
} else {
	if ((fabs(goal_position.pose.position.x - msg.transform.translation.x) < 0.1 ) && (fabs(goal_position.pose.position.y - msg.transform.translation.y) < 0.1) && (fabs(goal_position.pose.position.z - msg.transform.translation.z) < 0.1))
{
		ROS_INFO("Reached the waypoint!");

                waypoint_counter++;
		turn_check = 1;

		goal_position.pose.position.x = waypoints.at(waypoint_counter).x;
		goal_position.pose.position.y = waypoints.at(waypoint_counter).y;
		goal_position.pose.position.z = waypoints.at(waypoint_counter).z;

		ROS_INFO("Setting new waypoint to: [%0.2f, %0.2f, %0.2f]", goal_position.pose.position.x, goal_position.pose.position.y, goal_position.pose.position.z);
        }
}
  }
  }

int main(int argc, char **argv)
{
    ros::init(argc, argv, "offb_node");
    ros::NodeHandle nh;

    std::ifstream file("/home/quas/catkin_ws_g4/src/obnav/src/waypoints.txt");
    std::string str;

    ROS_INFO("Loading waypoints...");
    
    double row, x, y, z;
    while (std::getline(file, str)) {

        std::stringstream  lineStream(str);
        char  comma;
        lineStream >> row >> comma >> x >> comma >> y >> comma >> z;

	geometry_msgs::Point tmp_waypoint;
        tmp_waypoint.x = x;
        tmp_waypoint.y = y;
        tmp_waypoint.z = z;

	ROS_INFO("Loading waypoint: [%0.2f, %0.2f, %0.2f]", x, y, z);

	waypoints.push_back(tmp_waypoint);
    }

	ROS_INFO("Done!");

	//image processing target found subscriber

	ros::Subscriber target_found = 	rospy.subscribe('target_message',10);
	
	//end

    ros::Subscriber state_sub = nh.subscribe<mavros_msgs::State>
            ("mavros/state", 10, state_cb);
    ros::Subscriber pos_sub = nh.subscribe<geometry_msgs::TransformStamped>
            ("vicon/hawkmoth/hawkmoth", 10, positionCallback);
    ros::Publisher local_pos_pub = nh.advertise<geometry_msgs::PoseStamped>
            ("/breadcrumb/reference/external", 10);
    ros::ServiceClient arming_client = nh.serviceClient<mavros_msgs::CommandBool>
            ("mavros/cmd/arming");
    ros::ServiceClient set_mode_client = nh.serviceClient<mavros_msgs::SetMode>
            ("mavros/set_mode");

    //the setpoint publishing rate MUST be faster than 2Hz
    ros::Rate rate(20.0);

    // wait for FCU connection
    while(ros::ok() && current_state.connected){
        ros::spinOnce();
        rate.sleep();
    }
    
    goal_position.pose.position.x = waypoints.at(waypoint_counter).x;
    goal_position.pose.position.y = waypoints.at(waypoint_counter).y;
    goal_position.pose.position.z = waypoints.at(waypoint_counter).z;

	goal_position.pose.orientation.w = 1;

    //send a few setpoints before starting
    for(int i = 100; ros::ok() && i > 0; --i){
        local_pos_pub.publish(goal_position);
        ros::spinOnce();
        rate.sleep();
    }

    mavros_msgs::SetMode offb_set_mode;
    offb_set_mode.request.custom_mode = "OFFBOARD";

    mavros_msgs::CommandBool arm_cmd;
    arm_cmd.request.value = true;

    ros::Time last_request = ros::Time::now();

    while(ros::ok()){


		goal_position.header.stamp = ros::Time::now ();
        local_pos_pub.publish(goal_position);
        ros::spinOnce();
        rate.sleep();
    }

    return 0;
}
