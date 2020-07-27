package com.bih.nic.pacsmemberentry;

import android.content.Context;
import android.util.Log;

import com.bih.nic.pacsmemberentry.Model.AcptdRjctdJobOfferEntity;
import com.bih.nic.pacsmemberentry.Model.ApproveWorkSiteEntity;
import com.bih.nic.pacsmemberentry.Model.BenDetails;
import com.bih.nic.pacsmemberentry.Model.BlkCompanyJobDetailsEntity;
import com.bih.nic.pacsmemberentry.Model.BlockJobOfferPostedEntity;
import com.bih.nic.pacsmemberentry.Model.BlockWeb;
import com.bih.nic.pacsmemberentry.Model.ConsolidatedReportModel;
import com.bih.nic.pacsmemberentry.Model.DefaultResponse;
import com.bih.nic.pacsmemberentry.Model.DepartmentLoginEntity;
import com.bih.nic.pacsmemberentry.Model.DepartmentMaster;
import com.bih.nic.pacsmemberentry.Model.DepartmentWiseVacancy;
import com.bih.nic.pacsmemberentry.Model.District;
import com.bih.nic.pacsmemberentry.Model.EmpRegDetails;
import com.bih.nic.pacsmemberentry.Model.EmployerDetails;
import com.bih.nic.pacsmemberentry.Model.JobListEntity;
import com.bih.nic.pacsmemberentry.Model.JobOfferPostedEntity;
import com.bih.nic.pacsmemberentry.Model.PacsMemeberEntity;
import com.bih.nic.pacsmemberentry.Model.PaymentStatusEntity;
import com.bih.nic.pacsmemberentry.Model.Questionnaire_entity;
import com.bih.nic.pacsmemberentry.Model.SkillMaster;
import com.bih.nic.pacsmemberentry.Model.SubDepartmentWiseVacancy;
import com.bih.nic.pacsmemberentry.Model.SubSkillMaster;
import com.bih.nic.pacsmemberentry.Model.UserDetails;
import com.bih.nic.pacsmemberentry.Model.Versioninfo;
import com.bih.nic.pacsmemberentry.Model.WorkDetailsEntity;
import com.bih.nic.pacsmemberentry.Model.WorkRequirementsEntity;
import com.bih.nic.pacsmemberentry.Model.WorkSiteEmployeeReportEntity;
import com.bih.nic.pacsmemberentry.Model.WorkerModel;
import com.bih.nic.pacsmemberentry.Model.WrkReqApprovalDetailsEntity;
import com.bih.nic.pacsmemberentry.Model.panchayat;
import com.bih.nic.pacsmemberentry.Model.ward_model;
import com.bih.nic.pacsmemberentry.Model.workListModel;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.protocol.HTTP;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static org.apache.http.util.EntityUtils.getContentCharSet;


/**
 * Created by nicsi on 3/17/2018.
 */
public class WebserviceHelper implements KvmSerializable {


    private Context ctx;

    public static final String SERVICENAMESPACE = "http://Covid19Help.bih.nic.in/";
    public static final String SERVICEURL = "http://www.covid19help.bih.nic.in/Covid19HelpWebservice.asmx";

//    public static final String SERVICENAMESPACE = "http://10.133.20.159/";
//    public static final String SERVICEURL = "http://10.133.20.159/TestService/MigrantJobSearchWebservice.asmx";

    private static final String AuthenticateUser = "Authenticate";
    private static final String AuthenticateORGUser = "AuthenticateOrgLogin";
    private static final String AuthenticateDPTUser = "AuthenticateDPtLogin";
    private static final String GETBENEFICIARYLIST="getAadhaar";
    private static final String UpdateMobile_UID="UpdateMobile_UID";
    private static final String BLOCK_METHOD="getBlock";
    private static final String SKILL_METHOD="SkilMasterList";
    private static final String Dept_METHOD="DepartmentList";
    private static final String JOB_SEARCH_METHOD="JobSearchDetails1";
    private static final String GET_JOB_REQUEST_METHOD="GetJobRequest";
    private static final String GET_JOB_Offer_Posted_METHOD="GetJobOfferOrg";
    private static final String GET_Work_Details_Approval="BenApprovalbyOrgAdm";
    private static final String GET_Work_Details_ApprovalBy_Dst="BenApprovalbyDSTAdm";
    private static final String GET_DEPT_WISE_VACENCY_METHOD="rpt_DepartmentWiseVacancy";
    private static final String GET_WORKSITE_EMPLOYEE="WorkEmployeeDtls";
    private static final String GET_JOB_Offer_Posted_HQ="GetJobOfferOrg";
    private static final String GET_JOB_Offer_block_Posted_METHOD="GetJobOfferBlockwise";
    private static final String GET_Acpt_Rjct_Job_By_Labour="GetJobOfferLabourDetails";
    private static final String GET_Blk_Wise_company_Jobs="GetCompanayBlockWise";
    private static final String GET_Work_Requirement_approval="BenApprovalbyOrgAdmWorkRequirement";
    private static final String GET_Work_Requirement_approval_By_Dst="BenApprovalbyDSTAdmWorkRequirement";
    private static final String UPDATE_PROFILE_IMAGE_METHOD="UpdateImage";
    private static final String SUBSKILL_METHOD="SubSkilMasterList";
    private static final String DISTRICT_METHOD="getDistrict";
    private static final String PANCHAYAT_METHOD="getPanchyat";
    private static final String UpdateUID_Status="UpdateUID_Status";
    private static final String SEARCHGLOBAL="getAadharbyid";
    private static final String GETAADHAARUSERDETAIL="getAadharUserDetail";
    private static final String UploadDataForMobNo_chng="VerifyDetail";
    private static final String GET_BEN_DETAILS="getuserDetails";
    private static final String GET_PAYMENT_DETAILS="GetPaymentDtls";
    private static final String UpdateMobile="updateUserMob";
    private static final String Reques_tOTP="ResendVerifyOtp";
    private static final String UpdateAadhar_Status="UpdateAadhar_Status";
    private static final String UpdateAliveCertificate="UpdateAliveCertificate";
    private static final String UPLOAD_METHOD="InsertDetails";
    private static final String BindWard="BindWard";
    private static final String INSERT_WORK_DETAIL="InsertWorkDataNew";

    public static final  String APPVERSION_METHOD = "getAppLatest";
    public static final  String UpdateUserDetails = "UpdateUserDetails";
    public static final  String AcceptRjctRecordsFromPacs = "UpdateRequest";
    public static final  String GetWorkDetails = "GetWorkDetails";
    public static final  String GetRequirmentData = "GetRequirmentData";
    public static final  String WorkSite_eDit = "WorkSiteSearchList";
    public static final  String Work_req_edit = "WorkRequiremenDtl";
    public static final  String GetLoadLabourData = "GetLoadLabourData";
    public static final  String EmployerRegistration_METHOD = "InsertCompanyDtls";
    public static final  String REGISTRATION_EMP_MOB_METHOD = "OrgsendOtp";
    public static final  String ConsolidatedRptForStatus = "ConsolidatedRptForStatus";
    public static final  String ConsolidatedRptForDistWiseBlockStatus = "ConsolidatedRptForDistWiseBlockStatus";
    public static final  String Approve_Work_Site = "BenApprovalbyOrgAdmUpdate";
    public static final  String Approve_Work_Site_By_dst = "BenApprovalbyDSTAdmUpdate";

    private static final String GET_SUB_DEPT_WISE_VACENCY_METHOD="rpt_DepartmentWiseVacancyORG";
    private static final String InsertData_Ben="rpt_DepartmentWiseVacancyORG";

    static String rest;


    public static ArrayList<District> getDistrictData()
    {

        SoapObject res1;
        res1=getServerData(DISTRICT_METHOD, District.DISTRICT_CLASS);
        int TotalProperty=0;
        if(res1!=null) TotalProperty= res1.getPropertyCount();
        ArrayList<District> fieldList = new ArrayList<District>();

        for (int i = 0; i < TotalProperty; i++)
        {
            if (res1.getProperty(i) != null)
            {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    District sm = new District(final_object);
                    fieldList.add(sm);
                }
            }
            else
            {
                return fieldList;
            }

        }

        return fieldList;
    }

    public static DefaultResponse updateProfileImage(String regId, String lat, String longi, String img)
    {

        SoapObject request = new SoapObject(SERVICENAMESPACE, UPDATE_PROFILE_IMAGE_METHOD);

        request.addProperty("_RegistrationNo",regId);
        request.addProperty("_Latlong", lat);
        request.addProperty("_Longitude",longi);
        request.addProperty("_Image",img);
        DefaultResponse userDetails;
        SoapObject res1;

        try
        {
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE, DefaultResponse.DefaultResponse_CLASS.getSimpleName(), DefaultResponse.DefaultResponse_CLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + UPDATE_PROFILE_IMAGE_METHOD, envelope);

            res1 = (SoapObject) envelope.getResponse();

            int TotalProperty = res1.getPropertyCount();

            userDetails = new DefaultResponse(res1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return userDetails;
    }

    public static ArrayList<JobListEntity> searchJobMasterData(String regId, String distId)
    {

        SoapObject res1;
        res1=getServerData(GET_JOB_REQUEST_METHOD, JobListEntity.JobListEntity_CLASS, "RegistrationNo", regId);
        int TotalProperty=0;
        if(res1!=null) TotalProperty= res1.getPropertyCount();

        ArrayList<JobListEntity> fieldList = new ArrayList<JobListEntity>();

        for (int i = 0; i < TotalProperty; i++)
        {
            if (res1.getProperty(i) != null)
            {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    JobListEntity block= new JobListEntity(final_object);
                    fieldList.add(block);
                }
            }
            else
            {
                return fieldList;
            }

        }

        return fieldList;
    }

    public static ArrayList<SkillMaster> getSkillMasterData()
    {

        SoapObject res1;
        res1=getServerData(SKILL_METHOD, SkillMaster.SkillMaster_CLASS);
        int TotalProperty=0;
        if(res1!=null) TotalProperty= res1.getPropertyCount();

        ArrayList<SkillMaster> fieldList = new ArrayList<SkillMaster>();

        for (int i = 0; i < TotalProperty; i++)
        {
            if (res1.getProperty(i) != null)
            {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    SkillMaster block= new SkillMaster(final_object);
                    fieldList.add(block);
                }
            }
            else{
                return fieldList;
            }

        }

        return fieldList;
    }

    public static ArrayList<SubSkillMaster> getSubSkillMasterData()
    {

        SoapObject res1;
        res1=getServerData(SUBSKILL_METHOD, SubSkillMaster.SubSkillMaster_CLASS);
        int TotalProperty=0;
        if(res1!=null) TotalProperty= res1.getPropertyCount();

        ArrayList<SubSkillMaster> fieldList = new ArrayList<SubSkillMaster>();

        for (int i = 0; i < TotalProperty; i++)
        {
            if (res1.getProperty(i) != null)
            {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    SubSkillMaster block= new SubSkillMaster(final_object);
                    fieldList.add(block);
                }
            }
            else{
                return fieldList;
            }

        }

        return fieldList;
    }

    public static ArrayList<BlockWeb> getBlockData(String distCode)
    {

        SoapObject res1;
        res1=getServerData(BLOCK_METHOD, BlockWeb.BlockWeb_CLASS,"District_Code",distCode);
        int TotalProperty=0;
        if(res1!=null) TotalProperty= res1.getPropertyCount();

        ArrayList<BlockWeb> fieldList = new ArrayList<BlockWeb>();

        for (int i = 0; i < TotalProperty; i++)
        {
            if (res1.getProperty(i) != null)
            {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    BlockWeb block= new BlockWeb(final_object);
                    fieldList.add(block);
                }
            }
            else
            {
                return fieldList;
            }

        }

        return fieldList;
    }

    public static ArrayList<ward_model> getBenByWard(String benId)
    {

        SoapObject res1;
        res1=getServerData(BindWard, ward_model.WARD_MODEL_CLASS,"ID",benId);
        int TotalProperty=0;
        if(res1!=null) TotalProperty= res1.getPropertyCount();

        ArrayList<ward_model> fieldList = new ArrayList<ward_model>();

        for (int i = 0; i < TotalProperty; i++)
        {
            if (res1.getProperty(i) != null)
            {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    ward_model block= new ward_model(final_object);
                    fieldList.add(block);
                }
            }
            else
            {
                return fieldList;
            }

        }

        return fieldList;
    }

    public static ArrayList<panchayat> getPanchayatData(String panch)
    {

        SoapObject res1;
        res1=getServerData(PANCHAYAT_METHOD, panchayat.panchayat,"_BlockCode",panch);
        int TotalProperty=0;
        if(res1!=null) TotalProperty= res1.getPropertyCount();

        ArrayList<panchayat> fieldList = new ArrayList<panchayat>();

        for (int i = 0; i < TotalProperty; i++)
        {
            if (res1.getProperty(i) != null)
            {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    panchayat block= new panchayat(final_object);
                    fieldList.add(block);
                }
            }
            else{
                return fieldList;
            }

        }

        return fieldList;
    }
    public static ArrayList<workListModel> GetWorkDetails(String panch)
    {

        SoapObject res1;
        res1=getServerData(GetWorkDetails, panchayat.panchayat,"orgId",panch);
        int TotalProperty=0;
        if(res1!=null) TotalProperty= res1.getPropertyCount();

        ArrayList<workListModel> fieldList = new ArrayList<workListModel>();

        for (int i = 0; i < TotalProperty; i++)
        {
            if (res1.getProperty(i) != null)
            {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    workListModel block= new workListModel(final_object);
                    fieldList.add(block);
                }
            }
            else
            {
                return fieldList;
            }

        }

        return fieldList;
    }

    public static UserDetails Login(String User_ID, String Pwd)
    {
        try
        {
            SoapObject res1;
            res1=getServerData(AuthenticateUser, UserDetails.getUserClass(),"_UserId","_Password",User_ID,Pwd);
            if (res1 != null)
            {
                return new UserDetails(res1);
            }
            else
                {
                return null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public static UserDetails loginUser(String User_ID, String Pwd) {
        try {
            SoapObject res1;
            res1=getServerData(AuthenticateUser, UserDetails.getUserClass(),"RegistrationNo","OTP",User_ID,Pwd);
            if (res1 != null) {
                return new UserDetails(res1);
            } else
                return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static EmployerDetails EmployerloginUser(String User_ID, String Pwd) {
        try {
            SoapObject res1;
            res1=getServerData(AuthenticateORGUser, UserDetails.getUserClass(),"_UserId","_Password",User_ID,Pwd);
            if (res1 != null) {
                return new EmployerDetails(res1);
            } else
                return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static SoapObject getServerData(String methodName,Class bindClass)
    {
        SoapObject res1;
        try {
            SoapObject request = new SoapObject(SERVICENAMESPACE,methodName);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            // SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,bindClass.getSimpleName(),bindClass);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + methodName,envelope);
            res1 = (SoapObject) envelope.getResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return res1;
    }


    public static SoapObject getServerData(String methodName,Class bindClass,String param1,String param2,String value1,String value2 )
    {
        SoapObject res1;
        try {
            SoapObject request = new SoapObject(SERVICENAMESPACE,methodName);
            request.addProperty(param1,value1);
            request.addProperty(param2,value2);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,bindClass.getSimpleName(),bindClass);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + methodName,envelope);
            res1 = (SoapObject) envelope.getResponse();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return res1;
    }

    public static SoapObject getServerData(String methodName,Class bindClass,String param,String value )
    {
        SoapObject res1;
        try {
            SoapObject request = new SoapObject(SERVICENAMESPACE,methodName);
            request.addProperty(param,value);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,bindClass.getSimpleName(),bindClass);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + methodName,envelope);
            res1 = (SoapObject) envelope.getResponse();
        }

        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return res1;
    }

    public static ArrayList<BenfiList> beneficiaryData(String distcode,String blockcode,String panchayatID) {



        SoapObject request = new SoapObject(SERVICENAMESPACE, GETBENEFICIARYLIST);


        request.addProperty("DistCode",distcode);
        request.addProperty("BlockCode",blockcode);
        request.addProperty("PanchayatCode",panchayatID);

        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,BenfiList.BENFICLASS.getSimpleName(), BenfiList.BENFICLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL,60000);
            androidHttpTransport.call(SERVICENAMESPACE + GETBENEFICIARYLIST, envelope);

            res1 = (SoapObject) envelope.getResponse();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();
        ArrayList<BenfiList> pvmArrayList = new ArrayList<BenfiList>();
        if(TotalProperty>0)
        {

            for (int ii = 0; ii < TotalProperty; ii++)
            {
                if (res1.getProperty(ii) != null)
                {
                    Object property = res1.getProperty(ii);
                    if (property instanceof SoapObject)
                    {
                        SoapObject final_object = (SoapObject) property;
                        BenfiList state = new BenfiList(final_object);
                        pvmArrayList.add(state);
                    }
                }
                else
                {
                    return pvmArrayList;
                }

            }
        }


        return pvmArrayList;
    }
    /*  public static ArrayList<BenfiList> SearchById(String benfiId) {



          SoapObject request = new SoapObject(SERVICENAMESPACE, SEARCADAARBYID);


          request.addProperty("benid",benfiId);

          SoapObject res1;
          try {

              SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
              envelope.dotNet = true;
              envelope.setOutputSoapObject(request);
              envelope.addMapping(SERVICENAMESPACE,
                      BenfiList.getdata.getSimpleName(), BenfiList.getdata);
              HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL,60000);
              androidHttpTransport.call(SERVICENAMESPACE + SEARCADAARBYID, envelope);

              res1 = (SoapObject) envelope.getResponse();

          } catch (Exception e) {
              e.printStackTrace();
              return null;
          }
          int TotalProperty = res1.getPropertyCount();
          ArrayList<BenfiList> pvmArrayList = new ArrayList<BenfiList>();
          if(TotalProperty>0) {


              for (int ii = 0; ii < TotalProperty; ii++) {
                  if (res1.getProperty(ii) != null) {
                      Object property = res1.getProperty(ii);
                      if (property instanceof SoapObject) {
                          SoapObject final_object = (SoapObject) property;
                          BenfiList state = new BenfiList(final_object);
                          pvmArrayList.add(state);
                      }
                  } else
                      return pvmArrayList;
              }
          }


          return pvmArrayList;
      }*/
    public static ArrayList<BenfiList> SearchGlobal(String benid) {


        SoapObject request = new SoapObject(SERVICENAMESPACE, SEARCHGLOBAL);


        request.addProperty("benid",benid);

        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,
                    BenfiList.BENFICLASS.getSimpleName(), BenfiList.BENFICLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL,60000);
            androidHttpTransport.call(SERVICENAMESPACE + SEARCHGLOBAL, envelope);

            res1 = (SoapObject) envelope.getResponse();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();
        ArrayList<BenfiList> pvmArrayList = new ArrayList<BenfiList>();
        if(TotalProperty>0) {
            for (int ii = 0; ii < TotalProperty; ii++) {
                if (res1.getProperty(ii) != null) {
                    Object property = res1.getProperty(ii);
                    if (property instanceof SoapObject) {
                        SoapObject final_object = (SoapObject) property;
                        BenfiList state = new BenfiList(final_object);
                        pvmArrayList.add(state);
                    }
                } else
                    return pvmArrayList;
            }
        }


        return pvmArrayList;
    }



    public static String UpDateAdhaar(String data,String mobileno,String uid) {
        try {
            SoapObject request = new SoapObject(SERVICENAMESPACE,
                    UpdateMobile_UID);
            request.addProperty("_BenId",data);
            request.addProperty("_Mobile",mobileno);
            request.addProperty("_UID",uid);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + UpdateMobile_UID, envelope);
            Object result = envelope.getResponse();
            if (result != null) {
                return result.toString();
            } else
                return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public static String UpdateUidStatus(BenfiList benfiList) {
        try {
            SoapObject request = new SoapObject(SERVICENAMESPACE,
                    UpdateUID_Status);
            request.addProperty("_BenId",benfiList.getBeneficiary_id());
            request.addProperty("_UID_Status",benfiList.getUidStatus());
            request.addProperty("_NameInAdhhar",benfiList.getModifiedName());
            request.addProperty("_uid",benfiList.getAadharNumber());
            // request.addProperty("_DOB",dob);
            // request.addProperty("_UidVerifyDate",currentdate);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + UpdateUID_Status, envelope);
            Object result = envelope.getResponse();
            if (result != null) {
                return result.toString();
            } else
                return null;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public static String UpdateAliveCertificate(String id,String status)
    {
        try
        {
            SoapObject request = new SoapObject(SERVICENAMESPACE,UpdateAliveCertificate);
            request.addProperty("_BenId",id);
            request.addProperty("_JeevanPramanStatus",status);

            // request.addProperty("_DOB",dob);
            // request.addProperty("_UidVerifyDate",currentdate);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + UpdateAliveCertificate, envelope);
            Object result = envelope.getResponse();
            if (result != null)
            {
                return result.toString();
            }
            else
                return null;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
    public static String UpdateAliveCertificate1(BenfiList benfiList) {
        try {
            SoapObject request = new SoapObject(SERVICENAMESPACE,
                    UpdateAliveCertificate);
            request.addProperty("_BenId",benfiList.getBeneficiary_id());
            request.addProperty("_JeevanPramanStatus",benfiList.getUidStatus());

            // request.addProperty("_DOB",dob);
            // request.addProperty("_UidVerifyDate",currentdate);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + UpdateAliveCertificate, envelope);
            Object result = envelope.getResponse();
            if (result != null)
            {
                return result.toString();
            }
            else
            {
                return null;
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
    public static String UpdateAadhar_Status(BenfiList data)
    {
        try
        {
            SoapObject request = new SoapObject(SERVICENAMESPACE,UpdateAadhar_Status);
            request.addProperty("_DistCode",data.getDistcode());
            request.addProperty("_BlockCode",data.getBlockcode());
            request.addProperty("_BenId",data.getBeneficiary_id());
            request.addProperty("_Aadhhar",data.getAadharNumber());
            request.addProperty("_NameInAadhhar",data.getBeneficiery_name());
            request.addProperty("_UpdatedBy",data.getEntryby());
            // request.addProperty("_DOB",dob);
            // request.addProperty("_UidVerifyDate",currentdate);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + UpdateAadhar_Status, envelope);
            Object result = envelope.getResponse();
            if (result != null)
            {
                return result.toString();
            }
            else{
                return null;
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
    @Override
    public Object getProperty(int index) {
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 0;
    }

    @Override
    public void setProperty(int index, Object value) {

    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {

    }


    public static String Upload(BenfiList data,String userid,String date)
    {
        try
        {
            SoapObject request = new SoapObject(SERVICENAMESPACE,UPLOAD_METHOD);

            request.addProperty("_BeneficiaryId",data.getBeneficiary_id());
            request.addProperty("_Dist",data.getDistcode());
            request.addProperty("_Block",data.getBlockcode());
            request.addProperty("_Panchayat",data.getBlocjname());
            request.addProperty("_NameAsPerAadhar",data.getNameASPerAdhaar());
            request.addProperty("_BenName",data.getBeneficiery_name());
            request.addProperty("_BankName",data.getNameInPass());
            request.addProperty("_AadharNo",data.getAadharNumber());//_FYearID
            request.addProperty("_Ward",data.getWard());
            request.addProperty("_MobileNo",data.getBen_Mobile());
            request.addProperty("_YearOfBirth",data.getYearOfBorth());
            request.addProperty("_Photo1",data.getPhoto1());
            request.addProperty("_photo2",data.getPhoto2());
            request.addProperty("_Photo3",data.getPhoto3());
            request.addProperty("_Photo4",data.getPhoto4());
            request.addProperty("_EntryBy",userid);
            request.addProperty("_EntryDate",date);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,BenfiList.BENFICLASS.getSimpleName(),BenfiList.BENFICLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL,50000);
            androidHttpTransport.call(SERVICENAMESPACE + UPLOAD_METHOD,envelope);
            Object result = envelope.getResponse();
            if (result != null)
            {
                Log.d("efghegehg", result.toString());

                return result.toString();
            }
            else
                return null;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
    public static Versioninfo CheckVersion(String imei, String version)
    {
        Versioninfo versioninfo;
        SoapObject res1;
        try
        {

            res1=getServerData(APPVERSION_METHOD, Versioninfo.Versioninfo_CLASS,"IMEI","Ver",imei,version);
            SoapObject final_object = (SoapObject) res1.getProperty(0);

            versioninfo = new Versioninfo(final_object);

        }
        catch (Exception e)
        {

            return null;
        }
        return versioninfo;

    }


    public static DefaultResponse UploadFinalData(String regno, String aadharno,String benname,String gendercode)
    {

        SoapObject request = new SoapObject(SERVICENAMESPACE, UploadDataForMobNo_chng);

        request.addProperty("RegistrationNo",regno);
        request.addProperty("AdharNo", aadharno);
        request.addProperty("Name",benname);
        request.addProperty("Gender",gendercode);
        DefaultResponse userDetails;
        SoapObject res1;
        try
        {
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE, DefaultResponse.DefaultResponse_CLASS.getSimpleName(), DefaultResponse.DefaultResponse_CLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + UploadDataForMobNo_chng, envelope);

            res1 = (SoapObject) envelope.getResponse();

            int TotalProperty = res1.getPropertyCount();

            userDetails = new DefaultResponse(res1);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return userDetails;

    }

    public static PaymentStatusEntity getPaymentDetail(String regno)
    {

        SoapObject request = new SoapObject(SERVICENAMESPACE, GET_PAYMENT_DETAILS);

        request.addProperty("RegistrationNo",regno);

        PaymentStatusEntity userDetails;
        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE, PaymentStatusEntity.PaymentStatusEntity_CLASS.getSimpleName(), PaymentStatusEntity.PaymentStatusEntity_CLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GET_PAYMENT_DETAILS, envelope);

            res1 = (SoapObject) envelope.getResponse();

            int TotalProperty = res1.getPropertyCount();

            userDetails = new PaymentStatusEntity(res1);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return userDetails;

    }

    public static BenDetails getBen_Details(String regno) {

        SoapObject request = new SoapObject(SERVICENAMESPACE, GET_BEN_DETAILS);

        request.addProperty("RegistrationNo",regno);

        BenDetails userDetails;
        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE, BenDetails.USER_CLASS.getSimpleName(), BenDetails.USER_CLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GET_BEN_DETAILS, envelope);

            res1 = (SoapObject) envelope.getResponse();

            int TotalProperty = res1.getPropertyCount();

            userDetails = new BenDetails(res1);

        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return userDetails;

    }


    public static DefaultResponse UpdateMobileNumber(String regno, String newmobile)
    {

        SoapObject request = new SoapObject(SERVICENAMESPACE, UpdateMobile);

        request.addProperty("_registrationNo",regno);
        request.addProperty("_MobileNo", newmobile);

        DefaultResponse userDetails;
        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE, DefaultResponse.DefaultResponse_CLASS.getSimpleName(), DefaultResponse.DefaultResponse_CLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + UpdateMobile, envelope);

            res1 = (SoapObject) envelope.getResponse();

            int TotalProperty = res1.getPropertyCount();

            userDetails = new DefaultResponse(res1);

        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return userDetails;

    }

    public static DefaultResponse RequestOTP(String newmobile)
    {

        SoapObject request = new SoapObject(SERVICENAMESPACE, Reques_tOTP);

        //request.addProperty("_registrationNo",regno);
        request.addProperty("_MobileNo", newmobile);

        DefaultResponse userDetails;
        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE, DefaultResponse.DefaultResponse_CLASS.getSimpleName(), DefaultResponse.DefaultResponse_CLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + Reques_tOTP, envelope);

            res1 = (SoapObject) envelope.getResponse();

            int TotalProperty = res1.getPropertyCount();

            userDetails = new DefaultResponse(res1);

        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return userDetails;

    }
    public static String UpdateUserDetail(BenDetails benDetails,String RegId,String Date) {
        try {
            SoapObject request = new SoapObject(SERVICENAMESPACE,
                    UpdateUserDetails);
//            request.addProperty("_Dist",benDetails.getDistrictCode());
//            request.addProperty("_Block",benDetails.getBlockCode());

            request.addProperty("_Panchayat",benDetails.getPanchayatCode());
            request.addProperty("_Mobile",benDetails.getVchMobile());
            request.addProperty("_Account",benDetails.getVchBankAccount());
            request.addProperty("_BankName",benDetails.getVchBankName());
            request.addProperty("_IFSC",benDetails.getVchIfsc());
            request.addProperty("_Skill",benDetails.getSkill_Id());
            request.addProperty("_SubSkillId",benDetails.getSubSkillId());
            request.addProperty("_Qualification",benDetails.getIntQualification());
            request.addProperty("_Aadhaar",benDetails.getVchAadhaar());
            request.addProperty("_vchName",benDetails.getVchName());
            request.addProperty("_Reg",RegId);
            request.addProperty("_NoofExperienceYear",benDetails.getIntExpYears());
            request.addProperty("_NoofExperienceMonth",benDetails.getIntExpMonths());

            request.addProperty("_tQualificationId","");

            request.addProperty("_vchWorkAddress",benDetails.getVchWorkAddress());
            request.addProperty("_vchGuardian_name",benDetails.getVchGuardian_name());
            request.addProperty("_vchGuardian_number",benDetails.getVchGuardian_number());
            request.addProperty("_intAge",benDetails.getIntAge());
            request.addProperty("_vchAddress",benDetails.getVchAddress());
            request.addProperty("_intGender",benDetails.getIntGender());
            request.addProperty("_NameAsPerAadhar",benDetails.getNameAsPerAdhaar());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + UpdateUserDetails, envelope);
            Object result = envelope.getResponse();
            if (result != null)
            {
                return result.toString();
            }
            else
            {
                return null;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
    public static String VerifyAdhaar(String adhaar,String name) {
        try {
            SoapObject request = new SoapObject("http://tempuri.org/", "DemographicUIDAuth");

            request.addProperty("mobile_auth_aadhaar",adhaar);
            request.addProperty("name",name);
            request.addProperty("dob","");


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE("http://164.100.37.11/UIDAuthService/UIDAuth.asmx");
            androidHttpTransport.call("http://tempuri.org/" + "DemographicUIDAuth", envelope);
            Object result = envelope.getResponse();
            if (result != null) {
                return result.toString();
            } else
                return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Element getSoapPropert(Document doc, String key, String value)
    {
        Element eid = doc.createElement(key);
        eid.appendChild(doc.createTextNode(value));
        return eid;
    }

    public static String UploadWorkSiteDetail(Context context, WorkDetailsEntity workDetail, ArrayList<WorkRequirementsEntity> requirements, String orgId) {

        context=context;
        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        dbfac.setNamespaceAware(true);
        DocumentBuilder docBuilder = null;

        try {
            docBuilder = dbfac.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0, ParserConfigurationException!!";
        }
        DOMImplementation domImpl = docBuilder.getDOMImplementation();
        Document doc = domImpl.createDocument(SERVICENAMESPACE,    INSERT_WORK_DETAIL, null);
        doc.setXmlVersion("1.0");
        doc.setXmlStandalone(true);

        Element poleElement = doc.getDocumentElement();

        poleElement.appendChild(getSoapPropert(doc, "workid", workDetail.getWorksId()));
        poleElement.appendChild(getSoapPropert(doc, "_FinYear", workDetail.getFin_yr()));
        poleElement.appendChild(getSoapPropert(doc, "_DistCode",workDetail.getDist_id()));

        poleElement.appendChild(getSoapPropert(doc, "_BlockCode",workDetail.getBlk_id()));
        poleElement.appendChild(getSoapPropert(doc, "_worksiteEn",workDetail.getWork_site_eng()));
        poleElement.appendChild(getSoapPropert(doc, "_worksiteHn",workDetail.getWork_site_hn()));
        poleElement.appendChild(getSoapPropert(doc, "_locEn",workDetail.getLocation_en()));
        poleElement.appendChild(getSoapPropert(doc, "_locHn",workDetail.getLocation_hn()));
        poleElement.appendChild(getSoapPropert(doc, "_pin",workDetail.getPincode()));
        poleElement.appendChild(getSoapPropert(doc, "_conperEn",workDetail.getSupervisor_nm_en()));
        poleElement.appendChild(getSoapPropert(doc, "_conperHn",workDetail.getSupervisor_nm_hn()));

        poleElement.appendChild(getSoapPropert(doc, "_designationEn",""));
        poleElement.appendChild(getSoapPropert(doc, "_designationHn",""));

        poleElement.appendChild(getSoapPropert(doc, "_mobile",workDetail.getSupervisor_mob()));
        poleElement.appendChild(getSoapPropert(doc, "_depId",""));
        //poleElement.appendChild(getSoapPropert(doc, "_workStatus",""));

        poleElement.appendChild(getSoapPropert(doc, "_OrgId",orgId));
        poleElement.appendChild(getSoapPropert(doc, "_RelatedDept",workDetail.getRelated_dept()));

        //--------------Array-----------------//
        Element pdlsElement = doc.createElement("InsertFamilyNew");
        ArrayList<WorkRequirementsEntity> list = requirements;

        for(int x=0;x<list.size();x++)
        {
            Element pdElement = doc.createElement("InsertFamily");

            Element fid = doc.createElement("SkillCat");
            fid.appendChild(doc.createTextNode(list.get(x).getSkill_categId()));
            pdElement.appendChild(fid);

            Element vLebel = doc.createElement("SkillSubCat");
            vLebel.appendChild(doc.createTextNode(list.get(x).getSkill_sub_categId()));
            //vLebel.appendChild(doc.createTextNode("1234"));
            pdElement.appendChild(vLebel);

            Element vLebel2 = doc.createElement("NumPerson");
            vLebel2.appendChild(doc.createTextNode(list.get(x).getNo_of_persons()));
            pdElement.appendChild(vLebel2);

            Element vLebel3 = doc.createElement("Active");
            vLebel3.appendChild(doc.createTextNode(list.get(x).getIsActiveId()));
            pdElement.appendChild(vLebel3);

            Element vLebel4 = doc.createElement("Salary");
            vLebel4.appendChild(doc.createTextNode(list.get(x).getMin_salary()));
            pdElement.appendChild(vLebel4);

            Element vLebel5 = doc.createElement("StartDate");
            vLebel5.appendChild(doc.createTextNode(list.get(x).getStart_date()));
            pdElement.appendChild(vLebel5);

            Element vLebel6 = doc.createElement("SalaryMax");
            vLebel6.appendChild(doc.createTextNode(list.get(x).getMax_salary()));
            pdElement.appendChild(vLebel6);

            Element vLebel7 = doc.createElement("PersonExp");
            vLebel7.appendChild(doc.createTextNode(list.get(x).getMin_exp()));
            pdElement.appendChild(vLebel7);

            Element vLebel8 = doc.createElement("ExperianceMax");
            vLebel8.appendChild(doc.createTextNode(list.get(x).getMax_exp()));
            pdElement.appendChild(vLebel8);

            Element vLebel9 = doc.createElement("Gender");
            vLebel9.appendChild(doc.createTextNode(list.get(x).getGender()));
            pdElement.appendChild(vLebel9);

            Element vLebel10 = doc.createElement("SalaryType");
            vLebel10.appendChild(doc.createTextNode(list.get(x).getSalaryTypeId()));
            pdElement.appendChild(vLebel10);

            Element vLebel11 = doc.createElement("workredid");
            vLebel11.appendChild(doc.createTextNode(list.get(x).getWorksRegId()));
            pdElement.appendChild(vLebel11);

            pdlsElement.appendChild(pdElement);
        }
        poleElement.appendChild(pdlsElement);

        TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = null;
        String res = "0";
        try {

            try {
                trans = transfac.newTransformer();
            } catch (TransformerConfigurationException e1) {

                // TODO Auto-generated catch block

                e1.printStackTrace();
                return "0, TransformerConfigurationException";
            }

            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");

            // create string from xml tree
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(doc);

            BasicHttpResponse httpResponse = null;

            try {
                trans.transform(source, result);
            } catch (TransformerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "0, TransformerException";
            }

            String SOAPRequestXML = sw.toString();

            String startTag = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                    + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchem\" "
                    + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" >  "
                    + "<soap:Body > ";
            String endTag = "</soap:Body > " + "</soap:Envelope>";

            try{


                HttpPost httppost = new HttpPost(SERVICEURL);

                StringEntity sEntity = new StringEntity(startTag + SOAPRequestXML+ endTag,HTTP.UTF_8);

                sEntity.setContentType("text/xml;charset=UTF-8");
                httppost.setEntity(sEntity);
                HttpClient httpClient = new DefaultHttpClient();
                httpResponse = (BasicHttpResponse) httpClient.execute(httppost);

                HttpEntity entity = httpResponse.getEntity();

                if (httpResponse.getStatusLine().getStatusCode() == 200|| httpResponse.getStatusLine().getReasonPhrase().toString().equals("OK")) {
                    String output = _getResponseBody(entity);

                    res = parseRespnse(output);
                } else {
                    res = "0, Server no reponse";
                }
            }catch (Exception e){
                e.printStackTrace();
                return "0, Exception Caught";
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0, Exception Caught";
        }

        // response.put("HTTPStatus",httpResponse.getStatusLine().toString());
        return res;
    }

    public static String parseRespnse(String xml){
        String result = "Failed to parse";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        InputSource is;
        try {
            builder = factory.newDocumentBuilder();
            is = new InputSource(new StringReader(xml));
            Document doc = builder.parse(is);
            NodeList list = doc.getElementsByTagName("InsertWorkDataNewResult");
            result = list.item(0).getTextContent();
            //System.out.println(list.item(0).getTextContent());
        } catch (ParserConfigurationException e) {
        } catch (SAXException e) {
        } catch (IOException e) {
        }

        return result;
    }

    public static String _getResponseBody(final HttpEntity entity) throws IOException, ParseException {

        if (entity == null) { throw new IllegalArgumentException("HTTP entity may not be null"); }

        InputStream instream = entity.getContent();

        if (instream == null) { return ""; }

        if (entity.getContentLength() > Integer.MAX_VALUE) { throw new IllegalArgumentException(

                "HTTP entity too large to be buffered in memory"); }

        String charset = getContentCharSet(entity);

        if (charset == null)
        {

            charset = org.apache.http.protocol.HTTP.DEFAULT_CONTENT_CHARSET;

        }

        Reader reader = new InputStreamReader(instream, charset);

        StringBuilder buffer = new StringBuilder();

        try {

            char[] tmp = new char[1024];

            int l;

            while ((l = reader.read(tmp)) != -1) {

                buffer.append(tmp, 0, l);

            }

        }
        finally
        {

            reader.close();

        }

        return buffer.toString();

    }

    public static DefaultResponse UploadAcceptedRecordsFromPacs(JobListEntity data, String regNo) {

        SoapObject request = new SoapObject(SERVICENAMESPACE, AcceptRjctRecordsFromPacs);
        request.addProperty("_RequestId", data.getId());
        request.addProperty("_RegistrationNo",regNo);
        request.addProperty("_Status","Y");

        DefaultResponse response;
        SoapObject res1;

        try {
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + AcceptRjctRecordsFromPacs, envelope);

            res1 = (SoapObject) envelope.getResponse();

            int TotalProperty = res1.getPropertyCount();

            response = new DefaultResponse(res1);

        }
        catch (Exception e) {
            e.printStackTrace();
            //return "0";
            return null;
        }
        return response;

    }


    public static DefaultResponse UploadRejectedRecordsFromPacs(JobListEntity data, String regNo) {

        SoapObject request = new SoapObject(SERVICENAMESPACE, AcceptRjctRecordsFromPacs);
        request.addProperty("_RequestId", data.getId());
        request.addProperty("_RegistrationNo",regNo);
        request.addProperty("_Status","R");

        DefaultResponse response;
        SoapObject res1;

        try {
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + AcceptRjctRecordsFromPacs, envelope);

            res1 = (SoapObject) envelope.getResponse();

            int TotalProperty = res1.getPropertyCount();

            response = new DefaultResponse(res1);

        }
        catch (Exception e) {
            e.printStackTrace();
            //return "0";
            return null;
        }
        return response;

    }


    public static ArrayList<JobOfferPostedEntity> JobOfferPosted(String role, String orgid)
    {

        SoapObject request = new SoapObject(SERVICENAMESPACE, GET_JOB_Offer_Posted_METHOD);
        request.addProperty("Role", role);
        request.addProperty("orgId", orgid);

        SoapObject res1;
        try
        {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, JobOfferPostedEntity.JobOffer_CLASS.getSimpleName(), JobOfferPostedEntity.JobOffer_CLASS);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GET_JOB_Offer_Posted_METHOD,envelope);

            res1 = (SoapObject) envelope.getResponse();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<JobOfferPostedEntity> pvmArrayList = new ArrayList<JobOfferPostedEntity>();

        for (int ii = 0; ii < TotalProperty; ii++) {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    JobOfferPostedEntity panchayat = new JobOfferPostedEntity(final_object);
                    pvmArrayList.add(panchayat);
                }
            } else
                return pvmArrayList;
        }


        return pvmArrayList;
    }

    public static ArrayList<WorkSiteEmployeeReportEntity> getWorksiteEmployeeReport(String status, String districtCode, String blockCode, String deptId, String workId, String workRegId) {


        SoapObject request = new SoapObject(SERVICENAMESPACE, GET_WORKSITE_EMPLOYEE);

        request.addProperty("Status", status);
        request.addProperty("DistCode", districtCode);
        request.addProperty("blockCode", blockCode);
        request.addProperty("orgId", deptId);
        request.addProperty("WorkId", workId);
        request.addProperty("WorksRegId", workRegId);

        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, JobOfferPostedEntity.JobOffer_CLASS.getSimpleName(), JobOfferPostedEntity.JobOffer_CLASS);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GET_WORKSITE_EMPLOYEE,
                    envelope);

            res1 = (SoapObject) envelope.getResponse();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<WorkSiteEmployeeReportEntity> pvmArrayList = new ArrayList();

        for (int ii = 0; ii < TotalProperty; ii++) {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    WorkSiteEmployeeReportEntity result = new WorkSiteEmployeeReportEntity(final_object, status);
                    pvmArrayList.add(result);
                }
            } else
                return pvmArrayList;
        }

        return pvmArrayList;
    }

    public static ArrayList<DepartmentWiseVacancy> getDeptWiseVacencyReport(String status, String deptId) {


        SoapObject request = new SoapObject(SERVICENAMESPACE, GET_DEPT_WISE_VACENCY_METHOD);

        request.addProperty("_Status", status);
        request.addProperty("_deptid", deptId);

        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, JobOfferPostedEntity.JobOffer_CLASS.getSimpleName(), JobOfferPostedEntity.JobOffer_CLASS);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GET_DEPT_WISE_VACENCY_METHOD,
                    envelope);

            res1 = (SoapObject) envelope.getResponse();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<DepartmentWiseVacancy> pvmArrayList = new ArrayList<DepartmentWiseVacancy>();

        for (int ii = 0; ii < TotalProperty; ii++) {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    if(status.equalsIgnoreCase("ShowRec")) {
                        DepartmentWiseVacancy panchayat = new DepartmentWiseVacancy(final_object,"1");
                        pvmArrayList.add(panchayat);
                    }else if(status.equalsIgnoreCase("ShowOrg")) {
                        DepartmentWiseVacancy panchayat = new DepartmentWiseVacancy(final_object,"2");
                        pvmArrayList.add(panchayat);
                    }
                }
            } else
                return pvmArrayList;
        }

        return pvmArrayList;
    }
    public static ArrayList<BlockJobOfferPostedEntity> BlockJobOfferPosted(String distid,String orgid,String role) {

        SoapObject request = new SoapObject(SERVICENAMESPACE, GET_JOB_Offer_block_Posted_METHOD);
        request.addProperty("DistCode", distid);
        request.addProperty("orgId", orgid);
        request.addProperty("Role", role);

        SoapObject res1;
        try
        {
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, BlockJobOfferPostedEntity.BlockJobOffer_CLASS.getSimpleName(), BlockJobOfferPostedEntity.BlockJobOffer_CLASS);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GET_JOB_Offer_block_Posted_METHOD,
                    envelope);

            res1 = (SoapObject) envelope.getResponse();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<BlockJobOfferPostedEntity> pvmArrayList = new ArrayList<BlockJobOfferPostedEntity>();

        for (int ii = 0; ii < TotalProperty; ii++)
        {
            if (res1.getProperty(ii) != null)
            {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    BlockJobOfferPostedEntity panchayat = new BlockJobOfferPostedEntity(final_object);
                    pvmArrayList.add(panchayat);
                }
            }
            else
            {
                return pvmArrayList;
            }

        }

        return pvmArrayList;
    }

    public static ArrayList<AcptdRjctdJobOfferEntity>  JobOfferAcptdRjctd(String distid,String blkid, String orgid, String role,String status,String serialno)
    {
        SoapObject request = new SoapObject(SERVICENAMESPACE, GET_Acpt_Rjct_Job_By_Labour);
        request.addProperty("Distcode", distid);
        request.addProperty("BlockCode", blkid);
        request.addProperty("orgId", orgid);
        request.addProperty("Role", role);
        request.addProperty("Status", status);
        request.addProperty("SerialNo", serialno);
        SoapObject res1;
        try
        {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE, AcptdRjctdJobOfferEntity.AcptdRjctdOffer_CLASS.getSimpleName(), AcptdRjctdJobOfferEntity.AcptdRjctdOffer_CLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    SERVICEURL,60000);
            androidHttpTransport.call(SERVICENAMESPACE + GET_Acpt_Rjct_Job_By_Labour,envelope);

            res1 = (SoapObject) envelope.getResponse();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<AcptdRjctdJobOfferEntity> pvmArrayList = new ArrayList<AcptdRjctdJobOfferEntity>();

        for (int ii = 0; ii < TotalProperty; ii++)
        {
            if (res1.getProperty(ii) != null)
            {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    AcptdRjctdJobOfferEntity panchayat = new AcptdRjctdJobOfferEntity(final_object);
                    pvmArrayList.add(panchayat);
                }
            }
            else
            {
                return pvmArrayList;
            }

        }

        return pvmArrayList;
    }

    public static ArrayList<WorkerModel> GetRequirmentData(String distcode, String blockcode, String panchayatID)

    {
        SoapObject request = new SoapObject(SERVICENAMESPACE, GetRequirmentData);
        request.addProperty("DistrictCode",distcode);
        request.addProperty("WorkId",blockcode);
        request.addProperty("OrgId",panchayatID);

        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,BenfiList.BENFICLASS.getSimpleName(), BenfiList.BENFICLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL,60000);
            androidHttpTransport.call(SERVICENAMESPACE + GetRequirmentData, envelope);

            res1 = (SoapObject) envelope.getResponse();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();
        ArrayList<WorkerModel> pvmArrayList = new ArrayList<WorkerModel>();
        if(TotalProperty>0)
        {

            for (int ii = 0; ii < TotalProperty; ii++)
            {
                if (res1.getProperty(ii) != null)
                {
                    Object property = res1.getProperty(ii);
                    if (property instanceof SoapObject)
                    {
                        SoapObject final_object = (SoapObject) property;
                        WorkerModel state = new WorkerModel(final_object);
                        pvmArrayList.add(state);
                    }
                }
                else{
                    return pvmArrayList;
                }

            }
        }
        return pvmArrayList;

    }


    public static ArrayList<WorkerModel> GetLoadLabourData(String distcode, String SkillId, String Experiance, String Gender)
    {
        SoapObject request = new SoapObject(SERVICENAMESPACE, GetLoadLabourData);
        request.addProperty("DistrictCode",distcode);
        request.addProperty("SkillId",SkillId);
        request.addProperty("Experiance",Experiance);
        request.addProperty("Gender",Gender);
        SoapObject res1;
        try
        {
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,
                    BenfiList.BENFICLASS.getSimpleName(), BenfiList.BENFICLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL,60000);
            androidHttpTransport.call(SERVICENAMESPACE + GetLoadLabourData, envelope);

            res1 = (SoapObject) envelope.getResponse();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();
        ArrayList<WorkerModel> pvmArrayList = new ArrayList<WorkerModel>();
        if(TotalProperty>0)
        {
            for (int ii = 0; ii < TotalProperty; ii++)
            {
                if (res1.getProperty(ii) != null)
                {
                    Object property = res1.getProperty(ii);
                    if (property instanceof SoapObject)
                    {
                        SoapObject final_object = (SoapObject) property;
                        WorkerModel state = new WorkerModel(final_object,"1");
                        pvmArrayList.add(state);
                    }
                }
                else
                {
                    return pvmArrayList;
                }

            }
        }


        return pvmArrayList;
    }

    public static ArrayList<BlkCompanyJobDetailsEntity> BlkCompanyWiseJobOffers(String distid, String blkid, String orgid, String role)
    {


        SoapObject request = new SoapObject(SERVICENAMESPACE, GET_Blk_Wise_company_Jobs);

        request.addProperty("Distcode", distid);
        request.addProperty("BlockCode", blkid);
        request.addProperty("orgId", orgid);
        request.addProperty("Role", role);

        SoapObject res1;
        try
        {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, BlkCompanyJobDetailsEntity.BlockCompanyJobs_CLASS.getSimpleName(), BlkCompanyJobDetailsEntity.BlockCompanyJobs_CLASS);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GET_Blk_Wise_company_Jobs,envelope);

            res1 = (SoapObject) envelope.getResponse();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<BlkCompanyJobDetailsEntity> pvmArrayList = new ArrayList<BlkCompanyJobDetailsEntity>();

        for (int ii = 0; ii < TotalProperty; ii++)
        {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    BlkCompanyJobDetailsEntity panchayat = new BlkCompanyJobDetailsEntity(final_object);
                    pvmArrayList.add(panchayat);
                }
            }
//            {
//                return pvmArrayList;
//            }

        }


        return pvmArrayList;
    }

    public static String Registration_Mob(String mob,String otp) {
        try {
            SoapObject request = new SoapObject(SERVICENAMESPACE,REGISTRATION_EMP_MOB_METHOD);


            request.addProperty("_MobileNo",mob);
            request.addProperty("_OTP",otp);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,
                    EmpRegDetails.UserDetails_CLASS.getSimpleName(),
                    EmpRegDetails.UserDetails_CLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + REGISTRATION_EMP_MOB_METHOD,
                    envelope);

            Object result = envelope.getResponse();

            if (result != null) {
                // Log.d("", result.toString());

                return result.toString();
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
    public static DefaultResponse EmpRegistration(EmpRegDetails user)
    {
        SoapObject request = new SoapObject(SERVICENAMESPACE, EmployerRegistration_METHOD);
        request.addProperty("_CompanyName", user.getOrgName());
        request.addProperty("_CommpanyType", user.getOrgCode());
        request.addProperty("_DistCode", user.getDistCode());
        request.addProperty("_AddressName", user.getCrosspondance_address());
        request.addProperty("_CommpanyContactName", user.getContact_Person());
        request.addProperty("_ContactNo", user.getMobile_Number());
        request.addProperty("_OtherMobileNo", user.getAlternative_Mobile_Number());
        request.addProperty("_EmailId", user.getEmail());
        request.addProperty("_Password", user.getPassword());
        DefaultResponse userDetails;
        SoapObject res1;
        try
        {
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE, DefaultResponse.DefaultResponse_CLASS.getSimpleName(), DefaultResponse.DefaultResponse_CLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + EmployerRegistration_METHOD, envelope);

            res1 = (SoapObject) envelope.getResponse();

            int TotalProperty = res1.getPropertyCount();

            userDetails = new DefaultResponse(res1);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return userDetails;
//        try {
//            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
//                    SoapEnvelope.VER11);
//            envelope.dotNet = true;
//            envelope.implicitTypes = true;
//            envelope.setOutputSoapObject(request);
//
//            HttpTransportSE androidHttpTransport = new HttpTransportSE(
//                    SERVICEURL);
//            androidHttpTransport.call(SERVICENAMESPACE + UPLOAD_METHOD,
//                    envelope);
//            // res2 = (SoapObject) envelope.getResponse();
//            rest = envelope.getResponse().toString();
//
//            // rest=res2.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "0";
//        }
//        return rest;
    }


    public static ArrayList<DepartmentMaster> getDeptData()
    {

        SoapObject res1;
        res1=getServerData(Dept_METHOD, DepartmentMaster.DeptMaster_CLASS);
        int TotalProperty=0;
        if(res1!=null) TotalProperty= res1.getPropertyCount();

        ArrayList<DepartmentMaster> fieldList = new ArrayList<DepartmentMaster>();

        for (int i = 0; i < TotalProperty; i++)
        {
            if (res1.getProperty(i) != null)
            {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    DepartmentMaster block= new DepartmentMaster(final_object);
                    fieldList.add(block);
                }
            }
            else{
                return fieldList;
            }

        }

        return fieldList;
    }

    public static ArrayList<WorkDetailsEntity> GetWorkDetailDataForEdit(String status, String orgid) {



        SoapObject request = new SoapObject(SERVICENAMESPACE, WorkSite_eDit);


        request.addProperty("Status",status);
        request.addProperty("orgId",orgid);


        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,
                    WorkDetailsEntity.Work_details_CLASS.getSimpleName(), WorkDetailsEntity.Work_details_CLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL,60000);
            androidHttpTransport.call(SERVICENAMESPACE + WorkSite_eDit, envelope);

            res1 = (SoapObject) envelope.getResponse();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();
        ArrayList<WorkDetailsEntity> pvmArrayList = new ArrayList<WorkDetailsEntity>();
        if(TotalProperty>0) {


            for (int ii = 0; ii < TotalProperty; ii++) {
                if (res1.getProperty(ii) != null) {
                    Object property = res1.getProperty(ii);
                    if (property instanceof SoapObject) {
                        SoapObject final_object = (SoapObject) property;
                        WorkDetailsEntity state = new WorkDetailsEntity(final_object);
                        pvmArrayList.add(state);
                    }
                } else
                    return pvmArrayList;
            }
        }


        return pvmArrayList;
    }

    public static DepartmentLoginEntity DepartmentloginUser(String User_ID, String Pwd) {
        try {
            SoapObject res1;
            res1=getServerData(AuthenticateDPTUser, UserDetails.getUserClass(),"_UserId","_Password",User_ID,Pwd);
            if (res1 != null) {
                return new DepartmentLoginEntity(res1);
            } else
                return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public static ArrayList<WorkRequirementsEntity> GetWorkRequirementtForEdit(String workid) {

        SoapObject request = new SoapObject(SERVICENAMESPACE, Work_req_edit);


        request.addProperty("WorksId",workid);

        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,
                    WorkRequirementsEntity.Work_req_CLASS.getSimpleName(), WorkRequirementsEntity.Work_req_CLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL,60000);
            androidHttpTransport.call(SERVICENAMESPACE + Work_req_edit, envelope);

            res1 = (SoapObject) envelope.getResponse();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();
        ArrayList<WorkRequirementsEntity> pvmArrayList = new ArrayList<WorkRequirementsEntity>();
        if(TotalProperty>0) {


            for (int ii = 0; ii < TotalProperty; ii++) {
                if (res1.getProperty(ii) != null) {
                    Object property = res1.getProperty(ii);
                    if (property instanceof SoapObject) {
                        SoapObject final_object = (SoapObject) property;
                        WorkRequirementsEntity state = new WorkRequirementsEntity(final_object);
                        pvmArrayList.add(state);
                    }
                } else
                    return pvmArrayList;
            }
        }


        return pvmArrayList;
    }


    public static ArrayList<ConsolidatedReportModel> ConsolidatedReportDistrictWise(String distid, String blkid) {


        SoapObject request = new SoapObject(SERVICENAMESPACE, ConsolidatedRptForStatus);

        request.addProperty("Status", distid);
        request.addProperty("DistCode", blkid);


        SoapObject res1;
        try
        {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, BlkCompanyJobDetailsEntity.BlockCompanyJobs_CLASS.getSimpleName(), BlkCompanyJobDetailsEntity.BlockCompanyJobs_CLASS);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + ConsolidatedRptForStatus,envelope);

            res1 = (SoapObject) envelope.getResponse();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<ConsolidatedReportModel> pvmArrayList = new ArrayList<ConsolidatedReportModel>();

        for (int ii = 0; ii < TotalProperty; ii++)
        {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    ConsolidatedReportModel panchayat = new ConsolidatedReportModel(final_object);
                    pvmArrayList.add(panchayat);
                }
            }


        }


        return pvmArrayList;
    }
    public static ArrayList<ConsolidatedReportModel> ConsolidatedReportBlockWise(String distid, String blkid,String type) {


        SoapObject request = new SoapObject(SERVICENAMESPACE, ConsolidatedRptForDistWiseBlockStatus);

        request.addProperty("DistCode", distid);
        request.addProperty("BlockCode", blkid);
        request.addProperty("Status", type);

        SoapObject res1;
        try
        {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, BlkCompanyJobDetailsEntity.BlockCompanyJobs_CLASS.getSimpleName(), BlkCompanyJobDetailsEntity.BlockCompanyJobs_CLASS);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + ConsolidatedRptForDistWiseBlockStatus,envelope);

            res1 = (SoapObject) envelope.getResponse();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<ConsolidatedReportModel> pvmArrayList = new ArrayList<ConsolidatedReportModel>();

        for (int ii = 0; ii < TotalProperty; ii++)
        {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    if(type.equalsIgnoreCase("WRSA")) {
                        ConsolidatedReportModel panchayat = new ConsolidatedReportModel(final_object, 2);
                        pvmArrayList.add(panchayat);
                    }else if(type.equalsIgnoreCase("WRS")) {
                        ConsolidatedReportModel panchayat = new ConsolidatedReportModel(final_object, 1);
                        pvmArrayList.add(panchayat);
                    }else if(type.equalsIgnoreCase("WNPA")) {
                        ConsolidatedReportModel panchayat = new ConsolidatedReportModel(final_object, 1);
                        pvmArrayList.add(panchayat);
                    }else {
                        ConsolidatedReportModel panchayat = new ConsolidatedReportModel(final_object, 0);
                        pvmArrayList.add(panchayat);
                    }

                }
            }


        }


        return pvmArrayList;
    }

    public static ArrayList<JobOfferPostedEntity> JobOffersHQ(String role, String orgid) {


        SoapObject request = new SoapObject(SERVICENAMESPACE, GET_JOB_Offer_Posted_HQ);

        request.addProperty("Role", role);
        request.addProperty("orgId", orgid);

        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, JobOfferPostedEntity.JobOffer_CLASS.getSimpleName(), JobOfferPostedEntity.JobOffer_CLASS);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GET_JOB_Offer_Posted_HQ,
                    envelope);

            res1 = (SoapObject) envelope.getResponse();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<JobOfferPostedEntity> pvmArrayList = new ArrayList<JobOfferPostedEntity>();

        for (int ii = 0; ii < TotalProperty; ii++) {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    JobOfferPostedEntity panchayat = new JobOfferPostedEntity(final_object);
                    pvmArrayList.add(panchayat);
                }
            } else
                return pvmArrayList;
        }
        return pvmArrayList;
    }

    public static ArrayList<BlockJobOfferPostedEntity> BlockJobOfferPosted_HQ(String distid,String orgid,String role) {


        SoapObject request = new SoapObject(SERVICENAMESPACE, GET_JOB_Offer_block_Posted_METHOD);

        request.addProperty("DistCode", distid);
        request.addProperty("orgId", orgid);
        request.addProperty("Role", role);

        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, BlockJobOfferPostedEntity.BlockJobOffer_CLASS.getSimpleName(), BlockJobOfferPostedEntity.BlockJobOffer_CLASS);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GET_JOB_Offer_block_Posted_METHOD,
                    envelope);

            res1 = (SoapObject) envelope.getResponse();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<BlockJobOfferPostedEntity> pvmArrayList = new ArrayList<BlockJobOfferPostedEntity>();

        for (int ii = 0; ii < TotalProperty; ii++) {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    BlockJobOfferPostedEntity panchayat = new BlockJobOfferPostedEntity(final_object);
                    pvmArrayList.add(panchayat);
                }
            } else
                return pvmArrayList;
        }


        return pvmArrayList;
    }


    public static ArrayList<ApproveWorkSiteEntity> GetWorkSiteForApproval(String block, String deptid,String status)
    {
        SoapObject request = new SoapObject(SERVICENAMESPACE, GET_Work_Details_Approval);

        request.addProperty("_Block", block);
        request.addProperty("_deptid", deptid);
        request.addProperty("_Status", status);

        SoapObject res1;
        try
        {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, ApproveWorkSiteEntity.Approval_CLASS.getSimpleName(), ApproveWorkSiteEntity.Approval_CLASS);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GET_Work_Details_Approval,
                    envelope);

            res1 = (SoapObject) envelope.getResponse();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<ApproveWorkSiteEntity> pvmArrayList = new ArrayList<ApproveWorkSiteEntity>();

        for (int ii = 0; ii < TotalProperty; ii++)
        {
            if (res1.getProperty(ii) != null)
            {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    ApproveWorkSiteEntity panchayat = new ApproveWorkSiteEntity(final_object,"1");
                    pvmArrayList.add(panchayat);
                }
            }
            else
            {
                return pvmArrayList;
            }

        }
        return pvmArrayList;
    }


    public static ArrayList<WrkReqApprovalDetailsEntity> WorkRequirementForApproval(String workid) {


        SoapObject request = new SoapObject(SERVICENAMESPACE, GET_Work_Requirement_approval);

        request.addProperty("_Worksid", workid);

        SoapObject res1;
        try
        {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, WrkReqApprovalDetailsEntity.RequirementApproval_CLASS.getSimpleName(), WrkReqApprovalDetailsEntity.RequirementApproval_CLASS);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GET_Work_Requirement_approval,envelope);

            res1 = (SoapObject) envelope.getResponse();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<WrkReqApprovalDetailsEntity> pvmArrayList = new ArrayList<WrkReqApprovalDetailsEntity>();

        for (int ii = 0; ii < TotalProperty; ii++)
        {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    WrkReqApprovalDetailsEntity panchayat = new WrkReqApprovalDetailsEntity(final_object);
                    pvmArrayList.add(panchayat);
                }
            }
//            {
//                return pvmArrayList;
//            }

        }


        return pvmArrayList;
    }


    public static DefaultResponse WorksiteApprove(String wrkid, String userid, String verifytype,String remarks,String premarks)
    {

        SoapObject request = new SoapObject(SERVICENAMESPACE, Approve_Work_Site);

        request.addProperty("_Worksid",wrkid);
        request.addProperty("_verifiedBY", userid);
        request.addProperty("_VerifyType", verifytype);
        request.addProperty("_RejectionRemaks", remarks);
        request.addProperty("_VerifiedRemarksType", premarks);


        DefaultResponse userDetails;
        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE, DefaultResponse.DefaultResponse_CLASS.getSimpleName(), DefaultResponse.DefaultResponse_CLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + Approve_Work_Site, envelope);

            res1 = (SoapObject) envelope.getResponse();

            int TotalProperty = res1.getPropertyCount();

            userDetails = new DefaultResponse(res1);

        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return userDetails;

    }


    public static ArrayList<ApproveWorkSiteEntity> GetWorkSiteForApprovalByDst(String block,String status)
    {
        SoapObject request = new SoapObject(SERVICENAMESPACE, GET_Work_Details_ApprovalBy_Dst);

        request.addProperty("_Block", block);
        request.addProperty("_Status", status);

        SoapObject res1;
        try
        {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, ApproveWorkSiteEntity.Approval_CLASS.getSimpleName(), ApproveWorkSiteEntity.Approval_CLASS);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GET_Work_Details_ApprovalBy_Dst,
                    envelope);

            res1 = (SoapObject) envelope.getResponse();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<ApproveWorkSiteEntity> pvmArrayList = new ArrayList<ApproveWorkSiteEntity>();

        for (int ii = 0; ii < TotalProperty; ii++)
        {
            if (res1.getProperty(ii) != null)
            {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    ApproveWorkSiteEntity panchayat = new ApproveWorkSiteEntity(final_object,"2");
                    pvmArrayList.add(panchayat);
                }
            }
            else
            {
                return pvmArrayList;
            }

        }
        return pvmArrayList;
    }


    public static ArrayList<WrkReqApprovalDetailsEntity> WorkRequirementApprovalBy_Dst(String workid) {


        SoapObject request = new SoapObject(SERVICENAMESPACE, GET_Work_Requirement_approval_By_Dst);

        request.addProperty("_Worksid", workid);

        SoapObject res1;
        try
        {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, WrkReqApprovalDetailsEntity.RequirementApproval_CLASS.getSimpleName(), WrkReqApprovalDetailsEntity.RequirementApproval_CLASS);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GET_Work_Requirement_approval_By_Dst,envelope);

            res1 = (SoapObject) envelope.getResponse();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<WrkReqApprovalDetailsEntity> pvmArrayList = new ArrayList<WrkReqApprovalDetailsEntity>();

        for (int ii = 0; ii < TotalProperty; ii++)
        {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    WrkReqApprovalDetailsEntity panchayat = new WrkReqApprovalDetailsEntity(final_object);
                    pvmArrayList.add(panchayat);
                }
            }
//            {
//                return pvmArrayList;
//            }

        }


        return pvmArrayList;
    }


    public static DefaultResponse WorksiteApproveByDst(String wrkid, String userid, String verifytype,String remarks,String premarks)
    {

        SoapObject request = new SoapObject(SERVICENAMESPACE, Approve_Work_Site_By_dst);

        request.addProperty("_Worksid",wrkid);
        request.addProperty("_verifiedBY", userid);
        request.addProperty("_VerifyType", verifytype);
        request.addProperty("_RejectionRemaks", remarks);
        request.addProperty("_VerifiedRemarksType", premarks);


        DefaultResponse userDetails;
        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE, DefaultResponse.DefaultResponse_CLASS.getSimpleName(), DefaultResponse.DefaultResponse_CLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + Approve_Work_Site_By_dst, envelope);

            res1 = (SoapObject) envelope.getResponse();

            int TotalProperty = res1.getPropertyCount();

            userDetails = new DefaultResponse(res1);

        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return userDetails;

    }

    public static ArrayList<SubDepartmentWiseVacancy> getSubDeptWiseVacencyReport(String status, String deptId) {


        SoapObject request = new SoapObject(SERVICENAMESPACE, GET_SUB_DEPT_WISE_VACENCY_METHOD);

        request.addProperty("_Status", status);
        request.addProperty("_orgid", deptId);

        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, SubDepartmentWiseVacancy.DepartmentWiseVacancy_CLASS.getSimpleName(), SubDepartmentWiseVacancy.DepartmentWiseVacancy_CLASS);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GET_SUB_DEPT_WISE_VACENCY_METHOD,
                    envelope);

            res1 = (SoapObject) envelope.getResponse();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<SubDepartmentWiseVacancy> pvmArrayList = new ArrayList<SubDepartmentWiseVacancy>();

        for (int ii = 0; ii < TotalProperty; ii++) {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    if(status.equalsIgnoreCase("ShowRec")) {
                        SubDepartmentWiseVacancy panchayat = new SubDepartmentWiseVacancy(final_object,"1");
                        pvmArrayList.add(panchayat);
                    }else if(status.equalsIgnoreCase("ShowWrk")) {
                        SubDepartmentWiseVacancy panchayat = new SubDepartmentWiseVacancy(final_object,"2");
                        pvmArrayList.add(panchayat);
                    }else if(status.equalsIgnoreCase("ShowWrkReq")) {
                        SubDepartmentWiseVacancy panchayat = new SubDepartmentWiseVacancy(final_object,"3");
                        pvmArrayList.add(panchayat);
                    }
                }
            } else
                return pvmArrayList;
        }

        return pvmArrayList;
    }


    public static String VerifyAdhaar1(PacsMemeberEntity benfiList)
    {
        try
        {
            SoapObject request = new SoapObject("http://tempuri.org/", "DemographicUIDAuth");

            request.addProperty("mobile_auth_aadhaar",benfiList.getAadhaar_no());
            request.addProperty("name",benfiList.getFarmer_name());
            request.addProperty("dob",benfiList.getYob());
            //request.addProperty("Gender","");

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE("http://164.100.37.11/UIDAuthService/UIDAuth.asmx");
            androidHttpTransport.call("http://tempuri.org/" + "DemographicUIDAuth", envelope);
            Object result = envelope.getResponse();
            if (result != null)
            {
                return result.toString();
            }
            else
            {
                return null;
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public static DefaultResponse RegistrationNewBen(Questionnaire_entity user, String versoin) {
        DefaultResponse userDetails;
        SoapObject res1;
        try {
            SoapObject request = new SoapObject(SERVICENAMESPACE, InsertData_Ben);
//            request.addProperty("_Dist_Code",user.getDistCode());
//            request.addProperty("_BlockCode",user.getProjectCode());
//            request.addProperty("_panchayatCode",user.getPanchayatCode());
//            request.addProperty("_Other_panchayatName",user.getOther_Pan_Name());
//            request.addProperty("_benName",user.getBenificiaryName());
//            request.addProperty("_Benficiary_BirthYear",user.getDate_Of_Birth());
//            request.addProperty("_Gender",user.getGenderCode());
//            request.addProperty("_FName_aadhaar",user.getWifeName());
//            request.addProperty("_aadharnumber",user.getAadhaarNo());
//            request.addProperty("_StateCode",user.getStateCode());
//            request.addProperty("_Currentcity",user.getBen_Jagah());
//            request.addProperty("_MoblleNo",user.getMobileNo());
//            request.addProperty("_Ifsc",user.getIfsc());
//            request.addProperty("_Accountnumber",user.getAccountNo());
//            request.addProperty("_Photo",user.getPhoto1());
//            request.addProperty("_AadharImage",user.getAadhar_Photo());
//            request.addProperty("_AadharImgFront",user.getAadhar_front_Photo());
//            request.addProperty("_AadharImgBack",user.getAadhar_back_Photo());
//            request.addProperty("_latitude",user.getLatitude());
//            request.addProperty("_Longitude",user.getLongitude());
//            request.addProperty("_app_version",versoin);
//            request.addProperty("_encstring",API_Key);
            //request.addProperty("_Designation",user.getDateTime());


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,
                    Questionnaire_entity.Questionnaire_CLASS.getSimpleName(),
                    Questionnaire_entity.Questionnaire_CLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + InsertData_Ben, envelope);


            //Object result = envelope.getResponse();
            res1 = (SoapObject) envelope.getResponse();
            int TotalProperty = res1.getPropertyCount();

            userDetails = new DefaultResponse(res1);
//            if (result != null) {
//                // Log.d("", result.toString());
//
//                return result.toString();
//            } else


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return userDetails;
    }

}
