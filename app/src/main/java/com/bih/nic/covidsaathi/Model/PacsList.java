package com.bih.nic.covidsaathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class PacsList implements KvmSerializable, Serializable {

	private static final long serialVersionUID = 1L;

	public static Class<PacsList> PACS_CLASS = PacsList.class;
	private String _Pacs_Code = "";
	private String _Pacs_Name = "";


	public PacsList(SoapObject obj) {

		this._Pacs_Code = obj.getProperty("DistCode").toString();
		this._Pacs_Code = obj.getProperty("DistNameEn").toString();

	}

	public PacsList() {

	}



	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	public String get_Pacs_Code() {
		return _Pacs_Code;
	}

	public void set_Pacs_Code(String _Pacs_Code) {
		this._Pacs_Code = _Pacs_Code;
	}

	public String get_Pacs_Name() {
		return _Pacs_Name;
	}

	public void set_Pacs_Name(String _Pacs_Name) {
		this._Pacs_Name = _Pacs_Name;
	}
}
