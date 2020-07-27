package com.bih.nic.pacsmemberentry;

import android.location.Location;

import com.bih.nic.pacsmemberentry.Model.UserDetails;

import java.util.ArrayList;

public class GlobalVariables {

	public static UserDetails LoggedUser;
	public static boolean isOffline = false;
	public static boolean isLogin = false;
	public static String UserId="";
	
	public static String Last_Visited="";
	public static Location glocation=null;
	public static ArrayList ques_id;
	public static ArrayList answer_id;


}
