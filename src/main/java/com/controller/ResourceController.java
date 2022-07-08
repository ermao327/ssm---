package com.controller;

import com.entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.service.ResourceService;
import com.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    ResourceService resourceService;


    //mp4文件播放
    @RequestMapping("/play")
    public String play(HttpServletRequest request,String path,Integer resourceId) throws UnsupportedEncodingException {
      String name =  resourceService.selectname(resourceId);
        String path1 = path+name+".mp4";
        request.getSession().setAttribute("path",path1);
        System.out.println(path1+"==========================");
        return "/background/back_resourceDetailSet";
    }

    //下载资源
    @RequestMapping("/down")
    public void down(HttpSession session, Integer resourceId, HttpServletResponse resp) throws Exception {
//       session.setAttribute("resourceId", resourceId);
//        final Integer id1 = (Integer) session.getAttribute("resourceId");

        String path1 = resourceService.selectpath(resourceId);

        String Original_name=resourceService.selectOriginal_name(resourceId);

        String path = path1+"/"+Original_name+".pdf";
        //拼接文件名
        String fileName = Original_name+".pdf";

        FileInputStream in = new FileInputStream(path);
        BufferedInputStream bi = new BufferedInputStream(in);
        //解决文件名问题
        //1.文件名中文乱码
        //先把原文件名字符串用 gbk 编码成字节数组，再用 ISO-8859-1 解码回到字符串
        byte[] tmp = fileName.getBytes("gbk");
        String name = new String(tmp, "ISO-8859-1");

        //保存到客户端时有文件名称
        resp.setHeader("Content-Disposition", "attachment;filename=" + name);

        OutputStream out = resp.getOutputStream();
        BufferedOutputStream bo = new BufferedOutputStream(out);

        int data;
        while ((data = bi.read()) != -1) {
            bo.write(data);
        }
        bi.close();
        bo.close();
    }

    //后台修改用户状态禁用-启用
    @RequestMapping("/toggleStatus")
    @ResponseBody
    public void modifyCourseTypeStatus(HttpServletRequest request, Resource resource) {
        System.out.println("modifyStatus");

        try {
            resource.setId(resource.getId());
            resourceService.modifyStatus(resource);
        } catch (Exception e) {
            System.out.println("修改状态失败");
        }
    }



    //前端瀑布流查询资源
    @RequestMapping("/queryResource")
    @ResponseBody
    public AjaxResult queryResource(HttpServletRequest request) {
        AjaxResult ajaxResult = new AjaxResult();

        PageInfo<Resource> pageInfo = resourceService.queryResource();
        ajaxResult.setObj(pageInfo);

        return ajaxResult;
    }

    //获取课程选择列表
    @RequestMapping("/getList")
    @ResponseBody
    public AjaxResult getlist(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult result = new AjaxResult(true, "查询成功", null);
        try {
            List<Course> list = resourceService.selectCourseName();
            result.setObj(list);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    //根据课程名字查找对应课程章节
    @RequestMapping("/getChapter")
    @ResponseBody
    public AjaxResult getChapter(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult result = new AjaxResult(true, "查询成功", null);
        String course_name = String.valueOf(request.getParameter("course_name"));
        try {
            List<Chapter> list = resourceService.selectChapter(course_name);
            result.setObj(list);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    //问题描述： 做文件上传时，后台报错： Required request part ‘file‘ is not present
    //https://blog.csdn.net/LuckKing0925/article/details/107980033
    //修改资源信息
    @RequestMapping("/modifyFrontResource")
    @ResponseBody
    public String modifyFrontResource(@RequestParam(value = "multiFiles") MultipartFile file, HttpServletRequest request) {

        String id = String.valueOf(request.getParameter("id"));
        String title = String.valueOf(request.getParameter("title"));
        String cost_type1 = String.valueOf(request.getParameter("cost_type"));
        String cost_number1 = String.valueOf(request.getParameter("cost_number"));
        String chapter_id1 = String.valueOf(request.getParameter("chapter_id"));
        String file_size1 = String.valueOf(request.getParameter("file_size"));
        Integer cost_type = Integer.parseInt(cost_type1);
        Integer cost_number = Integer.parseInt(cost_number1);
        Integer chapter_id = Integer.parseInt(chapter_id1);
        Integer file_size = Integer.parseInt(file_size1);
        String course_name = String.valueOf(request.getParameter("course_name"));

        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        // 文件名
        String fileName = file.getOriginalFilename();

        //前缀名
        String prefix = fileName.substring(0, fileName.indexOf("."));

        // 后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);

        String filePath = "";
        if (suffixName.equals("pdf")) {
            // 上传后的路径-pdf文件
            filePath = "D:\\IDEA\\projects01\\src\\main\\webapp\\upload\\pdf\\";
        } else if (suffixName.equals("mp4")) {
            // 上传后的路径-mp4文件
            filePath = "D:\\IDEA\\projects01\\src\\main\\webapp\\upload\\mp4\\";
        }

        // 新文件名
//        fileName = UUID.randomUUID() + suffixName;//随机把名字随乱了，注掉
        File dest = new File(filePath + fileName);
        System.out.println("pdf文件路径为：" + dest.getPath());
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String original_name = prefix;
        String file_type = suffixName;
        String path = filePath;

        resourceService.modifyFrontResource(id, title, cost_type, cost_number,
                chapter_id, original_name, file_type, path, course_name, file_size);

        return fileName;

    }



    //添加资源信息
    @RequestMapping("/addFrontResource")
    @ResponseBody
    public String addFrontResource(@RequestParam(value = "multiFiles") MultipartFile file, HttpServletRequest request) {

        String title = String.valueOf(request.getParameter("title"));
        String cost_type1 = String.valueOf(request.getParameter("cost_type"));
        String cost_number1 = String.valueOf(request.getParameter("cost_number"));
        String chapter_id1 = String.valueOf(request.getParameter("chapter_id"));
        String file_size1 = String.valueOf(request.getParameter("file_size"));
        Integer cost_type = Integer.parseInt(cost_type1);
        Integer cost_number = Integer.parseInt(cost_number1);
        Integer chapter_id = Integer.parseInt(chapter_id1);
        Integer file_size = Integer.parseInt(file_size1);

        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        // 文件名
        String fileName = file.getOriginalFilename();

        //前缀名
        String prefix = fileName.substring(0, fileName.indexOf("."));

        // 后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);

        String filePath = "";
        if (suffixName.equals("pdf")) {
            // 上传后的路径-pdf文件
            filePath = "D:\\IDEA\\projects01\\src\\main\\webapp\\upload\\pdf\\";
        } else if (suffixName.equals("mp4")) {
            // 上传后的路径-mp4文件
            filePath = "D:\\IDEA\\projects01\\src\\main\\webapp\\upload\\mp4\\";
        }

        // 新文件名
//        fileName = UUID.randomUUID() + suffixName;//随机把名字随乱了，注掉
        File dest = new File(filePath + fileName);
        System.out.println("pdf文件路径为：" + dest.getPath());
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String original_name = prefix;
        String file_type = suffixName;
        String path = filePath;

        resourceService.addFrontResource( title, cost_type, cost_number,
                chapter_id, original_name, file_type, path, file_size);

        return fileName;

    }


    //分页查询资源信息
    @RequestMapping("/findBackResource")
    @ResponseBody
    public AjaxResult findBackUser(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        AjaxResult result = new AjaxResult();

        Integer pageNo = 1;
        if (!(request.getParameter("pageNo") == null || request.getParameter("pageNo").equals(""))) {
            pageNo = Integer.valueOf(request.getParameter("pageNo"));
        } else {
            pageNo = 1;
        }
        String title = String.valueOf(request.getParameter("title"));
        String login_name = String.valueOf(request.getParameter("login_name"));
        Integer status = Integer.valueOf(request.getParameter("status"));
        String file_type = String.valueOf(request.getParameter("file_type"));

        String create_start_date1 = String.valueOf(request.getParameter("create_start_date"));
        String create_end_date1 = String.valueOf(request.getParameter("create_end_date"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date create_start_date = sdf.parse("1900-01-01");
        java.util.Date create_end_date = sdf.parse("2999-12-31");
        if (create_start_date1 != null && !create_start_date1.equals("")) {

            create_start_date = sdf.parse(create_start_date1);
        }
        if (create_end_date1 != null && !create_end_date1.equals("")) {
            create_end_date = sdf.parse(create_end_date1);
        }

        PageInfo<Resource> info = resourceService.findResourceByAll(pageNo, title, login_name, status, file_type,
                create_start_date, create_end_date);
        result.setObj(info);

        return result;
    }

    //删除资源
    @RequestMapping("/removeFrontResource")
    @ResponseBody
    public void removeFrontResource(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        resourceService.removeFrontResource(id);
    }





}
