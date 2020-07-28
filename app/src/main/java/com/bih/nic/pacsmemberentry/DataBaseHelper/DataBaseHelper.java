package com.bih.nic.pacsmemberentry.DataBaseHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Base64;
import android.util.Log;

import com.bih.nic.pacsmemberentry.BenfiList;
import com.bih.nic.pacsmemberentry.Model.BlockWeb;
import com.bih.nic.pacsmemberentry.Model.CategoryMaster;
import com.bih.nic.pacsmemberentry.Model.DepartmentMaster;
import com.bih.nic.pacsmemberentry.Model.District;
import com.bih.nic.pacsmemberentry.Model.HospitalMastar;
import com.bih.nic.pacsmemberentry.Model.Organisation;
import com.bih.nic.pacsmemberentry.Model.SkillMaster;
import com.bih.nic.pacsmemberentry.Model.SubSkillMaster;
import com.bih.nic.pacsmemberentry.Model.UserDetails;
import com.bih.nic.pacsmemberentry.Model.panchayat;
import com.bih.nic.pacsmemberentry.Model.qualification;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DataBaseHelper  extends SQLiteOpenHelper {
    private static String DB_PATH = "";
    private static String DB_NAME = "AadharVer1.db";
///////private static String DB_NAME = "InspAwasN.db";

    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public DataBaseHelper(Context context)
    {
        super(context, DB_NAME, null, 1);
        if (android.os.Build.VERSION.SDK_INT >= 4.2)
        {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }
        else
            {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.myContext = context;
    }



    /**
     * Creates a empty database on the system and rewrites it with your own
     * database.
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist)
        {
            // do nothing - database already exist

        }
        else
            {

            // By calling this method and empty database will be created into
            // the default system path
            // of your application so we are gonna be able to overwrite that
            // database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            //this.getReadableDatabase();

            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.OPEN_READWRITE);


        } catch (SQLiteException e) {

            // database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;

    }

    public boolean databaseExist() {


        File dbFile = new File(DB_PATH + DB_NAME);

        return dbFile.exists();
    }

    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        // Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        this.getReadableDatabase().close();
        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();


    }

    public void openDataBase() throws SQLException {

        // Open the database

        this.getReadableDatabase();
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long setSkillMasterData(ArrayList<SkillMaster> distlist) {

        long c = -1;
        ArrayList<SkillMaster> dist = distlist;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete("SkilMaster",null,null);
        if (dist != null) {
            try {
                for (int i = 0; i < dist.size(); i++) {
                    values.put("Id", dist.get(i).getId());
                    values.put("SkillName", dist.get(i).getSkillName());
                    values.put("SkillNameHn", dist.get(i).getSkillNameHn());
                    c = db.insert("SkilMaster", null, values);
                }
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }

    public long setSubSkillMasterData(ArrayList<SubSkillMaster> distlist) {

        long c = -1;
        ArrayList<SubSkillMaster> dist = distlist;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete("SubSkillMaster",null,null);
        if (dist != null) {
            try {
                for (int i = 0; i < dist.size(); i++) {
                    values.put("SubskillId", dist.get(i).getId());
                    values.put("Sub_SkillName", dist.get(i).getSkillName());
                    values.put("Sub_SkillNameHn", dist.get(i).getSkillNameHn());
                    values.put("SkillId", dist.get(i).getSkillCategoryId());
                    c = db.insert("SubSkillMaster", null, values);
                }
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }

    public long setDistrictDataLocalUserWise(ArrayList<District> distlist) {

        long c = -1;
        ArrayList<District> dist = distlist;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete("District",null,null);
        if (dist != null) {
            try {
                for (int i = 0; i < dist.size(); i++) {
                    values.put("DistId", dist.get(i).get_DistCode());
                    values.put("DistName", dist.get(i).get_DistName());
                       c = db.insert("District", null, values);
                }
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }

    public long setBlockDataLocal(ArrayList<BlockWeb> blocklist, String distCode) {

        long c = -1;
        ArrayList<BlockWeb> block = blocklist;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.execSQL("Delete from Block");
        if (block.size() > 0) {
            try {


                for (int i = 0; i < block.size(); i++) {

                    values.put("BlockId", block.get(i).getBlockCode());
                    values.put("BlockName", block.get(i).getBlockName());
                    values.put("DistId", distCode);
                  /*  String[] param={block.get(i).getCode()};
                    long update = db.update("Block", values, "Code = ?", param);
                    if (!(update>0))*/
                    c = db.insert("Block", null, values);
                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }
    public long setPanchayatLocal(ArrayList<panchayat> blocklist, String distCode,String blockcode) {

        long c = -1;
        ArrayList<panchayat> block = blocklist;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        if (block.size() > 0) {
            try {


                for (int i = 0; i < block.size(); i++) {

                    values.put("panchId", block.get(i).getPanchayatId());
                    values.put("panchName", block.get(i).getPanchayatName());
                    values.put("distId", distCode);
                    values.put("blockId", blockcode);

                    c = db.insert("PanchayatList", null, values);
                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }

    public ArrayList<District> getDistDetail() {

        ArrayList<District> DistList = new ArrayList<District>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cur = db.rawQuery("SELECT * FROM  Districts ORDER BY DistName", null);
            int x = cur.getCount();

            while (cur.moveToNext()) {
                District distClass = new District();

                distClass.set_DistCode(cur.getString(cur.getColumnIndex("DistCode")));
                distClass.set_DistName(cur.getString(cur.getColumnIndex("DistName")));
                distClass.set_DistNameHN(cur.getString(cur.getColumnIndex("DistNameHN")));
                DistList.add(distClass);
            }


            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception

        }
        return DistList;

    }
     public ArrayList<qualification> Qualification() {

        ArrayList<qualification> DistList = new ArrayList<qualification>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cur = db.rawQuery("SELECT * FROM  QualificationMaster ", null);
            int x = cur.getCount();

            while (cur.moveToNext()) {
                qualification distClass = new qualification();

                distClass.setQualificationId(cur.getString(cur.getColumnIndex("ID")));
                distClass.setQualification_name(cur.getString(cur.getColumnIndex("VCHEDUCATION")));
                DistList.add(distClass);
            }


            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception

        }
        return DistList;

    }

    public ArrayList<CategoryMaster> getCategoryMasterList() {

        ArrayList<CategoryMaster> list = new ArrayList<CategoryMaster>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cur = db.rawQuery("SELECT * FROM  CasteCatogery", null);
            int x = cur.getCount();

            while (cur.moveToNext()) {
                CategoryMaster info = new CategoryMaster();

                info.setCat_id(cur.getString(cur.getColumnIndex("Id")));
                info.setCat_name(cur.getString(cur.getColumnIndex("name")));
                info.setCat_name_HinDi(cur.getString(cur.getColumnIndex("nameHN")));
                list.add(info);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return list;
    }

    public ArrayList<SkillMaster> getSkillMasterList() {

        ArrayList<SkillMaster> list = new ArrayList<SkillMaster>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cur = db.rawQuery("SELECT * FROM  SkilMaster", null);
            int x = cur.getCount();

            while (cur.moveToNext()) {
                SkillMaster info = new SkillMaster();

                info.setId(cur.getString(cur.getColumnIndex("Id")));
                info.setSkillName(cur.getString(cur.getColumnIndex("SkillName")));
                info.setSkillNameHn(cur.getString(cur.getColumnIndex("SkillNameHn")));
                list.add(info);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return list;
    }

    public ArrayList<SubSkillMaster> getSubSkillMasterList(String skillId) {

        ArrayList<SubSkillMaster> list = new ArrayList<SubSkillMaster>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cur = db.rawQuery("SELECT * FROM  SubSkillMaster WHERE SkillId = '"+ skillId +"'", null);
            int x = cur.getCount();

            while (cur.moveToNext()) {
                SubSkillMaster info = new SubSkillMaster();

                info.setId(cur.getString(cur.getColumnIndex("SubskillId")));
                info.setSkillName(cur.getString(cur.getColumnIndex("Sub_SkillName")));
                info.setSkillNameHn(cur.getString(cur.getColumnIndex("Sub_SkillNameHn")));
                info.setSkillCategoryId(cur.getString(cur.getColumnIndex("SkillId")));
                list.add(info);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return list;
    }

    public ArrayList<BlockWeb> getBlockDetail(String DistId) {

        ArrayList<BlockWeb> DistList = new ArrayList<BlockWeb>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cur = db.rawQuery("SELECT * FROM  Blocks where DistCode = '" + DistId + "'", null);
            int x = cur.getCount();

            while (cur.moveToNext()) {
                BlockWeb distClass = new BlockWeb();

                distClass.setBlockCode(cur.getString(cur.getColumnIndex("BlockCode")));
                distClass.setBlockName(cur.getString(cur.getColumnIndex("BlockName")));
                distClass.setDistCode(cur.getString(cur.getColumnIndex("DistCode")));
                distClass.setBlockNameHn(cur.getString(cur.getColumnIndex("BlockNameHN")));
                DistList.add(distClass);
            }


            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception

        }
        return DistList;

    }
    public ArrayList<panchayat> getpanchayatDetail(String DistId) {

        ArrayList<panchayat> DistList = new ArrayList<panchayat>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cur = db.rawQuery("SELECT * FROM  Panchayat where BlockCode = '" + DistId + "'", null);
            int x = cur.getCount();

            while (cur.moveToNext()) {
                panchayat panchayat = new panchayat();

                panchayat.setPanchayatId(cur.getString(cur.getColumnIndex("PanchayatCode")));
                panchayat.setPanchayatName(cur.getString(cur.getColumnIndex("PanchayatName")));
                panchayat.setBlockId(cur.getString(cur.getColumnIndex("BlockCode")));
                panchayat.setDistId(cur.getString(cur.getColumnIndex("DistrictCode")));
                DistList.add(panchayat);
            }


            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception

        }
        return DistList;

    }


    public String getBenImg(String uid) {

        String status="NA";
        try {

            SQLiteDatabase db = this.getReadableDatabase();

            String[] whereArgs = new String[]{uid};
            Cursor cur = db.rawQuery("SELECT Img_ben from UserDetails where UseId=?",whereArgs);
            int x = cur.getCount();
            while (cur.moveToNext()) {

                status = cur.getString(cur.getColumnIndex("Img_ben"));

            }
            cur.close();
            db.close();

        }
        catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception

        }
        return status;

    }

    //Insert User

    public long insertUserDetails(UserDetails result,String userid) {

        long c = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();


            ContentValues values = new ContentValues();

            values.put("UseId", userid);
            values.put("password", result.get_Passwoed());
            values.put("distcode", result.getDistCode());
            values.put("distname", result.getDistName());
            values.put("blockcode", result.getBlockCode());
            values.put("blockname", result.getBlockName());
            values.put("PanchayatCode", result.getPanchayatCode());
            values.put("PanchayatName", result.getPanchayatName());
            values.put("UserName", result.getUserName());
            values.put("Age", result.getAge());
            values.put("Mobile", result.getMobileNo());
            values.put("Address", result.getAddress());
          //  values.put("Email", result.getE());
            String[] whereArgs = new String[]{userid.toLowerCase()};

            c = db.update("UserDetails", values, "UseId=? ", whereArgs);

            if (!(c > 0)) {

                c = db.insert("UserDetails", null, values);
                //c = db.insertWithOnConflict("UserLogin", null, values,SQLiteDatabase.CONFLICT_REPLACE);
            }

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return c;

    }

    //Insert CheckedBeneficiary

  public long insertCheckedBeneficiary(BenfiList result,String userid) {

        long c = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();

            ContentValues values = new ContentValues();

            values.put("Name", result.getBeneficiery_name());
            values.put("BenId", result.getBeneficiary_id());
            values.put("DoB", "");
            values.put("NameInPassbook", result.getNameInPass());
            values.put("userid", result.getEntryby());
            values.put("distid", result.getDistcode());
            values.put("blockid", result.getBlockcode());
            values.put("panchytId", result.getPanchcode());
            values.put("Modified_name",result.getModifiedName());
            values.put("adhharno",result.getAadharNumber());
          //  values.put("Email", result.getE());
            String[] whereArgs = new String[]{result.getBeneficiary_id()};

            c = db.update("VerifiedBeneficiaryList", values, "BenId=? ", whereArgs);

            if (!(c > 0)) {

                c = db.insert("VerifiedBeneficiaryList", null, values);
                //c = db.insertWithOnConflict("UserLogin", null, values,SQLiteDatabase.CONFLICT_REPLACE);
            }

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return c;

    }
    public long insertLifeCertificate(BenfiList result,String userid) {

        long c = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();

            ContentValues values = new ContentValues();

            values.put("BenId", result.getBeneficiary_id());
            values.put("status", result.getLifeCertificate());
            values.put("userid", userid);
            String[] whereArgs = new String[]{result.getBeneficiary_id()};

            c = db.update("LifeCertificate", values, "BenId=? ", whereArgs);

            if (!(c > 0)) {

                c = db.insert("LifeCertificate", null, values);
                //c = db.insertWithOnConflict("UserLogin", null, values,SQLiteDatabase.CONFLICT_REPLACE);
            }

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return c;

    }
    public ArrayList<BenfiList> getAllVoucher(String userid) {

        ArrayList<BenfiList> progressList = new ArrayList<BenfiList>();
        try {
            String[] params = new String[]{userid };

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db
                    .rawQuery(
                            "select * from VerifiedBeneficiaryList WHERE BenId=? ", params);

            int count = cur.getCount();

            while (cur.moveToNext()) {

                BenfiList progress = new BenfiList();
                progress.setId(cur.getString(cur.getColumnIndex("id")));
                progress.setEntryby(cur.getString(cur.getColumnIndex("userid")));
                progress.setDistcode(cur.getString(cur.getColumnIndex("distid")));
                progress.setBlockcode( cur.getString(cur.getColumnIndex("blockid")));
                progress.setPanchcode( cur.getString(cur.getColumnIndex("panchytId")));
                progress.setBeneficiery_name( cur.getString(cur.getColumnIndex("Name")));
                progress.setBeneficiary_id(cur.getString(cur.getColumnIndex("BenId")));
                progress.setNameInPass( cur.getString(cur.getColumnIndex("NameInPassbook")));
                progress.setModifiedName( cur.getString(cur.getColumnIndex("Modified_name")));
                progress.setAadharNumber( cur.getString(cur.getColumnIndex("adhharno")));

                progressList.add(progress);

            }
            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception

        }
        return progressList;

    }  public ArrayList<BenfiList> getLifeCertificate(String userid) {

        ArrayList<BenfiList> progressList = new ArrayList<BenfiList>();
        try {
            String[] params = new String[]{userid };

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db
                    .rawQuery(
                            "select * from LifeCertificate WHERE BenId=? ", params);

            int count = cur.getCount();

            while (cur.moveToNext()) {

                BenfiList progress = new BenfiList();
                progress.setId(cur.getString(cur.getColumnIndex("BenId")));
                progress.setLifeCertificate(cur.getString(cur.getColumnIndex("status")));
                progress.setEntryby(cur.getString(cur.getColumnIndex("userid")));
                progressList.add(progress);

            }
            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception

        }
        return progressList;

    }

    public int getNumberTotalOfPendingData(String userId) {

        int x = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] whereArgs = {userId};
            Cursor cur = db.rawQuery("Select BenId from LifeCertificate where userid =?", whereArgs);
            x = cur.getCount();
            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return x;

    }

    public long deleteVerifiedAdaharUpload(String pid, String userId) {
        long c = -1;

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            String[] DeleteWhere = {String.valueOf(pid), userId};
            c = db.delete("VerifiedBeneficiaryList", "id=? and userid =?", DeleteWhere);

            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            return c;
        }
        return c;
    }

    public UserDetails getUserDetails(String userId) {

        UserDetails userInfo = null;

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            String[] params = new String[]{userId };

            Cursor cur = db.rawQuery(
                    "Select * from UserDetails WHERE UseId=? ", params);
            int x = cur.getCount();
            // db1.execSQL("Delete from UserDetail");

            while (cur.moveToNext()) {


                userInfo = new UserDetails();
               // userInfo.setAuthenticated(true);

                userInfo.set_UserId(cur.getString(cur.getColumnIndex("UseId")));
                userInfo.set_Passwoed(cur.getString(cur.getColumnIndex("password")));
                userInfo.setDistCode(cur.getString(cur.getColumnIndex("distcode")));
                userInfo.setDistName(cur.getString(cur.getColumnIndex("distname")));
                userInfo.setBlockCode(cur.getString(cur.getColumnIndex("blockcode")));
                userInfo.setBlockName(cur.getString(cur.getColumnIndex("blockname")));
                userInfo.setRole(cur.getString(cur.getColumnIndex("role")));


            }

            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            userInfo = null;
        }
        return userInfo;
    }



    public long ServergetBeneficiaryForWard(ArrayList<BenfiList> panchayatlist,String dist,String block) {

        long c = 0;
        try {
            ArrayList<BenfiList> panchayat = panchayatlist;
            SQLiteDatabase db = this.getWritableDatabase();
           // db.execSQL("Delete from BeneFiciaryList");
            ContentValues values = new ContentValues();
            if (panchayat.size() > 0) {


                for (int i = 0; i < panchayat.size(); i++) {

                    values.put("BenName", panchayat.get(i).getBeneficiery_name());
                    values.put("BenId", panchayat.get(i).getBeneficiary_id());
                    values.put("BenAadharNo", panchayat.get(i).getAadharNumber());
                    values.put("BenAccNo", panchayat.get(i).getAccountNo());
                    values.put("blockcode",block);
                    values.put("blockname",panchayat.get(i).getBlocjname());
                    values.put("distcode",dist);
                    values.put("distname",panchayat.get(i).getDistname());
                    values.put("uidStatus",panchayat.get(i).getUidStatus());
                    values.put("NameInPassbook",panchayat.get(i).getNameInPass());


                    String[] whereArgs = new String[]{panchayat.get(i).getBeneficiary_id()};


                    c = db.update(" BeneFiciaryList", values, "BenId=? ", whereArgs);

                    if (!(c > 0)) {

                        c = db.insert("BeneFiciaryList", null, values);
                    }
                }
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    public long ServergetBeneficiaryForWard1(ArrayList<BenfiList> panchayatlist,String blockCode,String panchytcode) {

        long c = 0;
        try {
            ArrayList<BenfiList> panchayat = panchayatlist;
            SQLiteDatabase db = this.getWritableDatabase();
             db.execSQL("Delete from BeneFiciaryList");
            ContentValues values = new ContentValues();
            if (panchayat.size() > 0) {


                for (int i = 0; i < panchayat.size(); i++) {

                    values.put("BenName", panchayat.get(i).getBeneficiery_name());
                    values.put("BenId", panchayat.get(i).getBeneficiary_id());
                    values.put("BenAadharNo", panchayat.get(i).getAadharNumber());
                    values.put("BenAccNo", panchayat.get(i).getAccountNo());
                    values.put("blockcode",blockCode);
                    values.put("blockname",panchayat.get(i).getBlocjname());
                    values.put("distcode",panchayat.get(i).getDistcode());
                    values.put("distname",panchayat.get(i).getDistname());
                    values.put("panchCode",panchytcode);
                    values.put("panchName",panchayat.get(i).getPanchname());
                    values.put("uidStatus",panchayat.get(i).getUidStatus());
                    values.put("NameInPassbook",panchayat.get(i).getNameInPass());



                    String[] whereArgs = new String[]{panchayat.get(i).getBeneficiary_id()};


                    c = db.update(" BeneFiciaryList", values, "BenId=? ", whereArgs);

                    if (!(c > 0)) {

                        c = db.insert("BeneFiciaryList", null, values);
                    }
                }
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }


    public ArrayList<BenfiList> getBenefilist( String blo) {

        // PlaceDataSQL placeData = new PlaceDataSQL(MainActivity.this);
        ArrayList<BenfiList> sectorList = new ArrayList<BenfiList>();
        Cursor cur;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[]{blo};
            cur = db.rawQuery("Select * from BeneFiciaryList WHERE panchCode=?", params);


            int x = cur.getCount();

            while (cur.moveToNext()) {

                BenfiList localSpinnerData = new BenfiList();
                localSpinnerData.setBeneficiary_id((cur.getString(cur.getColumnIndex("BenId"))));
                localSpinnerData.setBeneficiery_name(cur.getString(cur.getColumnIndex("BenName")));
                localSpinnerData.setAadharNumber(cur.getString(cur.getColumnIndex("BenAadharNo")));
                localSpinnerData.setBlockcode(cur.getString(cur.getColumnIndex("blockcode")));
                localSpinnerData.setBlocjname(cur.getString(cur.getColumnIndex("blockname")));
                localSpinnerData.setDistcode(cur.getString(cur.getColumnIndex("distcode")));
                localSpinnerData.setDistname(cur.getString(cur.getColumnIndex("distname")));
                localSpinnerData.setAccountNo(cur.getString(cur.getColumnIndex("BenAccNo")));
                localSpinnerData.setPanchcode(cur.getString(cur.getColumnIndex("panchCode")));
                localSpinnerData.setPanchname(cur.getString(cur.getColumnIndex("panchName")));
                localSpinnerData.setUidStatus(cur.getString(cur.getColumnIndex("uidStatus")));
                localSpinnerData.setNameInPass(cur.getString(cur.getColumnIndex("NameInPassbook")));


                sectorList.add(localSpinnerData);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return sectorList;

    }

    public int getBenTableSize(String distcode,String block) {

        int x = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] whereArgs = {distcode,block};
            Cursor cur = db.rawQuery("SELECT BenId FROM  BeneFiciaryList where blockcode = '" + block + "'" , null);
            x = cur.getCount();
            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return x;

    }

    public ArrayList<BenfiList> getAllSeverDataWIthFilter(String filter,int filterid) {

        ArrayList<BenfiList> progressList = new ArrayList<BenfiList>();
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = null;
            if(filterid==1){
                cur = db
                        .rawQuery(
                                "Select * from BeneFiciaryList WHERE BenAadharNo LIKE '%"+ filter +"%'" +"ORDER BY "  + "BenName"+" ASC ", null);

            }else if(filterid==2){

                cur = db
                        .rawQuery(
                                "Select * from BeneFiciaryList WHERE BenName LIKE '%"+ filter +"%'" +"ORDER BY "  + "BenName"+" ASC ", null);


            }else if(filterid==3){
                cur = db
                        .rawQuery(
                                "Select * from BeneFiciaryList WHERE BenAccNo LIKE '%"+ filter +"%'" +"ORDER BY "  + "BenName"+" ASC ", null);
            }else if(filterid==4){
                cur = db
                        .rawQuery(
                                "Select * from BeneFiciaryList WHERE BenId LIKE '%"+ filter +"%'" +"ORDER BY "  + "BenName"+" ASC ", null);

            }

           /* Cursor cur = db
                    .rawQuery(
                            "Select * from Beneficiary WHERE SenctionNo= '"+ filter +"'" +"ORDER BY "  + "BenName"+" ASC ", null);*/

            int count = cur.getCount();

            while (cur.moveToNext()) {

                BenfiList localSpinnerData = new BenfiList();
                localSpinnerData.setBeneficiary_id((cur.getString(cur.getColumnIndex("BenId"))));
                localSpinnerData.setBeneficiery_name(cur.getString(cur.getColumnIndex("BenName")));
                localSpinnerData.setAadharNumber(cur.getString(cur.getColumnIndex("BenAadharNo")));
                localSpinnerData.setBlockcode(cur.getString(cur.getColumnIndex("blockcode")));
                localSpinnerData.setBlocjname(cur.getString(cur.getColumnIndex("blockname")));
                localSpinnerData.setDistcode(cur.getString(cur.getColumnIndex("distcode")));
                localSpinnerData.setDistname(cur.getString(cur.getColumnIndex("distname")));
                localSpinnerData.setAccountNo(cur.getString(cur.getColumnIndex("BenAccNo")));
                localSpinnerData.setPanchcode(cur.getString(cur.getColumnIndex("panchCode")));
                localSpinnerData.setPanchname(cur.getString(cur.getColumnIndex("panchName")));
                localSpinnerData.setUidStatus(cur.getString(cur.getColumnIndex("uidStatus")));
                localSpinnerData.setNameInPass(cur.getString(cur.getColumnIndex("NameInPassbook")));
                progressList.add(localSpinnerData);
            }
            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception

        }
        return progressList;

    }

     public BenfiList getDataFilter(String filtername) {


         BenfiList localSpinnerData = new BenfiList();
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = null;
            cur=db.rawQuery("Select * from BeneFiciaryList WHERE BenAadharNo LIKE '%" +filtername + "%'"+ " OR " + " BenAccNo LIKE '%" +filtername+"%'" + " OR " + "BenId LIKE '%" +filtername+"%'",null);

          /*  if(filterid==1){
                cur = db
                        .rawQuery(
                                "Select * from BeneFiciaryList WHERE BenAadharNo LIKE '%"+ filter +"%'" +"ORDER BY "  + "BenName"+" ASC ", null);

            }else if(filterid==2){

                cur = db
                        .rawQuery(
                                "Select * from BeneFiciaryList WHERE BenName LIKE '%"+ filter +"%'" +"ORDER BY "  + "BenName"+" ASC ", null);


            }else if(filterid==3){
                cur = db
                        .rawQuery(
                                "Select * from BeneFiciaryList WHERE BenAccNo LIKE '%"+ filter +"%'" +"ORDER BY "  + "BenName"+" ASC ", null);
            }else if(filterid==4){
                cur = db
                        .rawQuery(
                                "Select * from BeneFiciaryList WHERE BenId LIKE '%"+ filter +"%'" +"ORDER BY "  + "BenName"+" ASC ", null);

            }*/

           /* Cursor cur = db
                    .rawQuery(
                            "Select * from Beneficiary WHERE SenctionNo= '"+ filter +"'" +"ORDER BY "  + "BenName"+" ASC ", null);
            */

            int count = cur.getCount();

            while (cur.moveToNext()) {


                localSpinnerData.setBeneficiary_id((cur.getString(cur.getColumnIndex("BenId"))));
                localSpinnerData.setBeneficiery_name(cur.getString(cur.getColumnIndex("BenName")));
                localSpinnerData.setAadharNumber(cur.getString(cur.getColumnIndex("BenAadharNo")));
                localSpinnerData.setBlockcode(cur.getString(cur.getColumnIndex("blockcode")));
                localSpinnerData.setBlocjname(cur.getString(cur.getColumnIndex("blockname")));
                localSpinnerData.setDistcode(cur.getString(cur.getColumnIndex("distcode")));
                localSpinnerData.setDistname(cur.getString(cur.getColumnIndex("distname")));
                localSpinnerData.setAccountNo(cur.getString(cur.getColumnIndex("BenAccNo")));
                localSpinnerData.setPanchcode(cur.getString(cur.getColumnIndex("panchCode")));
                localSpinnerData.setPanchname(cur.getString(cur.getColumnIndex("panchName")));
                localSpinnerData.setUidStatus(cur.getString(cur.getColumnIndex("uidStatus")));
                localSpinnerData.setNameInPass(cur.getString(cur.getColumnIndex("NameInPassbook")));

            }
            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception

        }
        return localSpinnerData;

    }
    public BenfiList getBenfiListToUpload(String filtername) {


        BenfiList localSpinnerData = new BenfiList();
        try {

            SQLiteDatabase db = this.getReadableDatabase();
           // Cursor cur = null;
            Cursor cur = db
                    .rawQuery(
                            "Select * from BeneFiciaryList WHERE BenId= '"+ filtername +"'" , null);
           // cur=db.rawQuery("Select * from BeneFiciaryList WHERE BenAadharNo LIKE '%" +filtername + "%'"+ " OR " + " BenAccNo LIKE '%" +filtername+"%'" + " OR " + "BenId LIKE '%" +filtername+"%'",null);

          /*  if(filterid==1){
                cur = db
                        .rawQuery(
                                "Select * from BeneFiciaryList WHERE BenAadharNo LIKE '%"+ filter +"%'" +"ORDER BY "  + "BenName"+" ASC ", null);

            }else if(filterid==2){

                cur = db
                        .rawQuery(
                                "Select * from BeneFiciaryList WHERE BenName LIKE '%"+ filter +"%'" +"ORDER BY "  + "BenName"+" ASC ", null);


            }else if(filterid==3){
                cur = db
                        .rawQuery(
                                "Select * from BeneFiciaryList WHERE BenAccNo LIKE '%"+ filter +"%'" +"ORDER BY "  + "BenName"+" ASC ", null);
            }else if(filterid==4){
                cur = db
                        .rawQuery(
                                "Select * from BeneFiciaryList WHERE BenId LIKE '%"+ filter +"%'" +"ORDER BY "  + "BenName"+" ASC ", null);

            }*/

           /* Cursor cur = db
                    .rawQuery(
                            "Select * from Beneficiary WHERE SenctionNo= '"+ filter +"'" +"ORDER BY "  + "BenName"+" ASC ", null);
            */

            int count = cur.getCount();

            while (cur.moveToNext()) {


                localSpinnerData.setBeneficiary_id((cur.getString(cur.getColumnIndex("BenId"))));
                localSpinnerData.setDistcode((cur.getString(cur.getColumnIndex("distcode"))));
                localSpinnerData.setBlockcode((cur.getString(cur.getColumnIndex("blockcode"))));
                localSpinnerData.setBlocjname((cur.getString(cur.getColumnIndex("blockname"))));
                localSpinnerData.setBeneficiery_name(cur.getString(cur.getColumnIndex("BenName")));
                localSpinnerData.setAadharNumber(cur.getString(cur.getColumnIndex("BenAadharNo")));
                localSpinnerData.setNameASPerAdhaar(cur.getString(cur.getColumnIndex("ben_name_asper_adhaar")));
                localSpinnerData.setWard(cur.getString(cur.getColumnIndex("wardId")));
                localSpinnerData.setBen_Mobile(cur.getString(cur.getColumnIndex("ben_movileno")));
                localSpinnerData.setYearOfBorth(cur.getString(cur.getColumnIndex("Ben_year_birth")));

                localSpinnerData.setNameInPass(cur.getString(cur.getColumnIndex("NameInPassbook")));
                localSpinnerData.setLatitude(cur.getString(cur.getColumnIndex("Lat")));
                localSpinnerData.setLongitude(cur.getString(cur.getColumnIndex("Lng")));
                String[] param2 = { cur.getString(cur.getColumnIndex("BenId"))};
                Cursor cur1 = db.rawQuery("select photo1 from BeneFiciaryList where Lat IS NOT NULL and Lng IS NOT NULL and  BenId = ? ", param2);
                Cursor cur2 = db.rawQuery("select photo2 from BeneFiciaryList where Lat IS NOT NULL and Lng IS NOT NULL and  BenId = ? ", param2);
                Cursor cur3 = db.rawQuery("select photo3 from BeneFiciaryList where Lat IS NOT NULL and Lng IS NOT NULL and  BenId = ? ", param2);
                Cursor cur4 = db.rawQuery("select photo4 from BeneFiciaryList where Lat IS NOT NULL and Lng IS NOT NULL and  BenId = ? ", param2);
                try {

                    while (cur1.moveToNext()) {
                        localSpinnerData.setPhoto1(cur1.isNull(cur1.getColumnIndex("photo1")) == false ? Base64
                                .encodeToString(
                                        cur1.getBlob(cur1.getColumnIndex("photo1")),
                                        Base64.NO_WRAP) : "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cur1.close();
                }
                try {
                    while (cur2.moveToNext()) {
                        localSpinnerData.setPhoto2(cur2.isNull(cur2.getColumnIndex("photo2")) == false ? Base64
                                .encodeToString(cur2.getBlob(cur2.getColumnIndex("photo2")), Base64.NO_WRAP) : "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cur2.close();
                }
                try {
                    while (cur3.moveToNext()) {
                        localSpinnerData.setPhoto3(cur3.isNull(cur3.getColumnIndex("photo3")) == false ? Base64
                                .encodeToString(
                                        cur3.getBlob(cur3.getColumnIndex("photo3")),
                                        Base64.NO_WRAP) : "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cur3.close();
                }
                try {
                    while (cur4.moveToNext()) {
                        localSpinnerData.setPhoto4(cur4.isNull(cur4.getColumnIndex("photo4")) == false ? Base64
                                .encodeToString(
                                        cur4.getBlob(cur4.getColumnIndex("photo4")),
                                        Base64.NO_WRAP) : "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cur4.close();
                }

              //  progressList.add(progress);



            }
            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception

        }
        return localSpinnerData;

    }

    public long getUserCount(String blockcode) {

        long x = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("Select * from BeneFiciaryList WHERE blockcode= '"+blockcode+"' ", null);

            x = cur.getCount();

            cur.close();
            db.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return x;
    }



    //update*********

    public long updateInspectionDetails(BenfiList result) {

        long c = -1;
        try {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("uidStatus", result.getUidStatus());
            values.put("BenName", result.getBeneficiery_name());

            c = db.update("BeneFiciaryList",  values,"BenId=?",new String[]{result.getBeneficiary_id()});
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            return c;
        }

        return c;

    }
    public long deletePendingUpload3(String pid) {
        long c = -1;

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            String[] DeleteWhere = {pid};
            c = db.delete("BeneFiciaryList", " BenId=?", DeleteWhere);
            Log.d("ftddgvgv", String.valueOf(c));

            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            return c;
        }
        return c;
    }
    public ArrayList<District> getDistrictLocal_new() {

        ArrayList<District> districtList = new ArrayList<District>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db
                    .rawQuery(
                            "SELECT * from  Districts order by DistNameHN", null);
            int x = cur.getCount();

            while (cur.moveToNext()) {

                District district = new District();
                district.set_DistCode(cur.getString(cur
                        .getColumnIndex("DistCode")));
                district.set_DistNameHN(cur.getString(cur.getColumnIndex("DistNameHN")));
                //district.setDistrictName(cur.getString(cur.getColumnIndex("DistName")));



                districtList.add(district);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception

        }
        return districtList;

    }
    public ArrayList<Organisation> getorg_new() {

        ArrayList<Organisation> districtList = new ArrayList<Organisation>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db
                    .rawQuery("SELECT * from  Organisation order by OrgCode", null);
            int x = cur.getCount();

            while (cur.moveToNext()) {

                Organisation district = new Organisation();
                district.set_OrgCode(cur.getString(cur
                        .getColumnIndex("OrgCode")));
                district.set_OrgName(cur.getString(cur.getColumnIndex("OrgName")));
                //district.setDistrictName(cur.getString(cur.getColumnIndex("DistName")));



                districtList.add(district);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception

        }
        return districtList;

    }

    public ArrayList<District> getDistrictLocal() {

        ArrayList<District> districtList = new ArrayList<District>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db
                    .rawQuery(
                            "SELECT * from  Districts order by DistName", null);
            int x = cur.getCount();

            while (cur.moveToNext()) {

                District district = new District();
                district.set_DistCode(cur.getString(cur
                        .getColumnIndex("DistCode")));
                district.set_DistNameHN(cur.getString(cur.getColumnIndex("DistName")));
                //district.setDistrictName(cur.getString(cur.getColumnIndex("DistName")));



                districtList.add(district);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception

        }
        return districtList;

    }
    public ArrayList<CategoryMaster> getCategoryLocal() {

        ArrayList<CategoryMaster> districtList = new ArrayList<CategoryMaster>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db
                    .rawQuery(
                            "SELECT * from  Category order by Category_Name", null);
            int x = cur.getCount();

            while (cur.moveToNext()) {

                CategoryMaster district = new CategoryMaster();
                district.setCat_id(cur.getString(cur
                        .getColumnIndex("Category_Code")));
                district.setCat_name(cur.getString(cur.getColumnIndex("Category_Name")));
                //district.setDistrictName(cur.getString(cur.getColumnIndex("DistName")));



                districtList.add(district);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception

        }
        return districtList;

    }
    public ArrayList<HospitalMastar> getHospital() {

        ArrayList<HospitalMastar> districtList = new ArrayList<HospitalMastar>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db
                    .rawQuery(
                            "SELECT * from  Hospital order by Hos_Name", null);
            int x = cur.getCount();

            while (cur.moveToNext()) {

                HospitalMastar district = new HospitalMastar();
                district.setHos_Code(cur.getString(cur
                        .getColumnIndex("Hos_Code")));
                district.setHos_Name(cur.getString(cur.getColumnIndex("Hos_Name")));
                //district.setDistrictName(cur.getString(cur.getColumnIndex("DistName")));



                districtList.add(district);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception

        }
        return districtList;

    }
    public ArrayList<Organisation> getorg() {

        ArrayList<Organisation> districtList = new ArrayList<Organisation>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db
                    .rawQuery(
                            "SELECT * from  Organisation order by OrgCode", null);
            int x = cur.getCount();

            while (cur.moveToNext()) {

                Organisation district = new Organisation();
                district.set_OrgCode(cur.getString(cur
                        .getColumnIndex("OrgCode")));
                district.set_OrgName(cur.getString(cur.getColumnIndex("OrgName")));
                //district.setDistrictName(cur.getString(cur.getColumnIndex("DistName")));



                districtList.add(district);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception

        }
        return districtList;

    }

    public String getNameFor(String tblName, String whereColumnName, String returnColumnValue, String thisID) {
        String thisValue = "";
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("select * from " + tblName + " WHERE " + whereColumnName + "='" + thisID.trim() + "'", null);
            int x = cur.getCount();
            while (cur.moveToNext()) {
                thisValue = cur.getString(cur.getColumnIndex(returnColumnValue));
            }
            cur.close();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thisValue.trim();
    }


    public ArrayList<DepartmentMaster> getDepartmentMasterList() {

        ArrayList<DepartmentMaster> list = new ArrayList<DepartmentMaster>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cur = db.rawQuery("SELECT * FROM  MasterDept", null);
            int x = cur.getCount();

            while (cur.moveToNext()) {
                DepartmentMaster info = new DepartmentMaster();

                info.setDeptId(cur.getString(cur.getColumnIndex("Dept_Id")));
                info.setDeptName(cur.getString(cur.getColumnIndex("Dept_Name")));
                info.setDeptNameHn(cur.getString(cur.getColumnIndex("DeptName_Hn")));
                info.setDept_Abb(cur.getString(cur.getColumnIndex("Dept_Abbrev")));
                info.setStatus(cur.getString(cur.getColumnIndex("status")));
                list.add(info);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return list;
    }


    public long setDeptMasterData(ArrayList<DepartmentMaster> distlist)
    {

        long c = -1;
        ArrayList<DepartmentMaster> dist = distlist;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete("MasterDept",null,null);
        if (dist != null) {
            try {
                for (int i = 0; i < dist.size(); i++) {
                    values.put("Dept_Id", dist.get(i).getDeptId());
                    values.put("Dept_Name", dist.get(i).getDeptName());
                    values.put("DeptName_Hn", dist.get(i).getDeptNameHn());
                    values.put("Dept_Abbrev", dist.get(i).getDept_Abb());
                    values.put("NameSymbol", dist.get(i).getName_Symbol());
                    values.put("status", dist.get(i).getStatus());
                    c = db.insert("MasterDept", null, values);
                }
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }

//    public long insertCategory(ArrayList<CategoryMaster> result) {
//
//        long c = -1;
//        try {
//
//            SQLiteDatabase db = this.getWritableDatabase();
//            //db.execSQL("Delete from Shg_Village_New");
//            // "SHG" ( `SHG_ID` TEXT, `SHG_NAME` TEXT, `PG_ID` TEXT, `PG_NAME` TEXT, `ID` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `Village_Code` TEXT, `Village_Name` TEXT )
//            for (CategoryMaster SHGType : result) {
//
//
//                ContentValues values = new ContentValues();
//                values.put("Category_Code", SHGType.getCat_id().trim());
//                values.put("Category_Name", SHGType.getCat_name().trim());
//
//                String[] whereArgs = new String[]{SHGType.getCat_id()};
//                c = db.update("Category", values, "Category_Code=?", whereArgs);
//                if (!(c > 0)) {
//                    c = db.insert("Category", null, values);
//                }
//
//            }
//            db.close();
//            getWritableDatabase().close();
//
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//        return c;
//    }
    public long insertCategory(CategoryMaster sevikaSahaikaEntity) {
        long c=-1;
        try {
            DataBaseHelper placeData = new DataBaseHelper(myContext);
            SQLiteDatabase db = placeData.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Category_Code",sevikaSahaikaEntity.getCat_id());
            contentValues.put("Category_Name",sevikaSahaikaEntity.getCat_name());
            String[] whereArgs = new String[]{sevikaSahaikaEntity.getCat_id()};
                c = db.update("Category", contentValues, "Category_Code=?", whereArgs);
                if (!(c > 0)) {
                    c = db.insert("Category", null, contentValues);
                }
            //c = db.insert("Category", null, contentValues);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return  c;
    }
    public long insertHospital(HospitalMastar sevikaSahaikaEntity) {
        long c=-1;
        try {
            DataBaseHelper placeData = new DataBaseHelper(myContext);
            SQLiteDatabase db = placeData.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Hos_Code",sevikaSahaikaEntity.getHos_Code());
            contentValues.put("Hos_Name",sevikaSahaikaEntity.getHos_Name());
            contentValues.put("Cat_Code",sevikaSahaikaEntity.getCat_Code());
            contentValues.put("Dist_Code",sevikaSahaikaEntity.getDist_Code());
            String[] whereArgs = new String[]{sevikaSahaikaEntity.getHos_Code()};
            c = db.update("Hospital", contentValues, "Hos_Code=?", whereArgs);
            if (!(c > 0)) {
                c = db.insert("Hospital", null, contentValues);
            }
            //c = db.insert("Category", null, contentValues);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return  c;
    }

}
