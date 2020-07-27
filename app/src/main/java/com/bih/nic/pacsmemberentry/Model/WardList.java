package com.bih.nic.pacsmemberentry.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class WardList implements KvmSerializable, Serializable
{

	private static final long serialVersionUID = 1L;

	public static Class<WardList> PACS_CLASS = WardList.class;
	private String _Ward_Code = "";
	private String _Ward_Name = "";
	private String _block_code = "";


	public WardList(SoapObject obj)
	{

		this._Ward_Code = obj.getProperty("DistCode").toString();
		this._Ward_Name = obj.getProperty("DistNameEn").toString();
		this._block_code = obj.getProperty("DistNameEn").toString();

	}

	public WardList()
	{

	}

	@Override
	public Object getProperty(int arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPropertyCount()
	{
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setProperty(int arg0, Object arg1)
	{
		// TODO Auto-generated method stub

	}

	public String get_Ward_Code()
	{
		return _Ward_Code;
	}

	public void set_Ward_Code(String _Ward_Code)
	{
		this._Ward_Code = _Ward_Code;
	}

	public String get_Ward_Name()
	{
		return _Ward_Name;
	}

	public void set_Ward_Name(String _Ward_Name)
	{
		this._Ward_Name = _Ward_Name;
	}

	public String get_block_code()
	{
		return _block_code;
	}

	public void set_block_code(String _block_code)
	{
		this._block_code = _block_code;
	}

}
