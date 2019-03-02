package com.example.sxd.thanksgivinghall.api;

import com.example.sxd.thanksgivinghall.api.service.BillService;
import com.example.sxd.thanksgivinghall.api.service.DeviceService;
import com.example.sxd.thanksgivinghall.api.service.LoginService;
import com.example.sxd.thanksgivinghall.api.service.NotifyService;
import com.example.sxd.thanksgivinghall.api.service.OfficeUserService;
import com.example.sxd.thanksgivinghall.api.service.PurReceiptService;
import com.example.sxd.thanksgivinghall.api.service.TaskService;
import com.example.sxd.thanksgivinghall.api.service.UpLoadService;
import com.example.sxd.thanksgivinghall.api.service.SupplierService;


public class AppMainService
{
    private static LoginService loginService;
    private static DeviceService deviceService;
    private static OfficeUserService officeUserService;
    private static TaskService taskService;
    private static NotifyService notifyService;
    private static UpLoadService upLoadService;
    private static SupplierService supplierService;
    private static PurReceiptService purReceiptService;
    private static BillService billService;



    public static LoginService getLoginService(String paramString)
    {
        loginService = (LoginService)BaseApi.retrofit(paramString).create(LoginService.class);
        return loginService;
    }
    public static DeviceService getDeviceService(String paramString)
    {
        deviceService = (DeviceService)BaseApi.retrofit(paramString).create(DeviceService.class);
        return deviceService;
    }
    public static OfficeUserService getOfficeUserService(String paramString)
    {
        officeUserService = (OfficeUserService)BaseApi.retrofit(paramString).create(OfficeUserService.class);
        return officeUserService;
    }
    public static TaskService getTaskService(String paramString)
    {
        taskService = (TaskService)BaseApi.retrofit(paramString).create(TaskService.class);
        return taskService;
    }
    public static NotifyService getNotifyService(String paramString)
    {
        notifyService = (NotifyService)BaseApi.retrofit(paramString).create(NotifyService.class);
        return notifyService;
    }
    public static UpLoadService getUpLoadService(String paramString)
    {
        upLoadService = (UpLoadService)BaseApi.retrofit(paramString).create(UpLoadService.class);
        return upLoadService;
    }
    public static SupplierService getsupplierService(String paramString)
    {
        supplierService = (SupplierService)BaseApi.retrofit(paramString).create(SupplierService.class);
        return supplierService;
    }
    public static PurReceiptService getpurReceiptService(String paramString)
    {
        purReceiptService = (PurReceiptService)BaseApi.retrofit(paramString).create(PurReceiptService.class);
        return purReceiptService;
    }
    public static BillService getBillService(String paramString)
    {
        billService = (BillService) BaseApi.retrofit(paramString).create(BillService.class);
        return billService;
    }
}