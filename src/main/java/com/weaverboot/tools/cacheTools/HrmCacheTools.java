package com.weaverboot.tools.cacheTools;

import weaver.hrm.company.DepartmentComInfo;
import weaver.hrm.company.SubCompanyComInfo;
import weaver.hrm.job.JobTitlesComInfo;
import weaver.hrm.resource.ResourceComInfo;

/**
 *
 * 此类用于组织人员同步后清除缓存
 *
 * @Author : Jaylan Zhou
 *
 */

public class HrmCacheTools {

    /**
     *
     * 清除人员信息缓存
     *
     * @throws Exception
     */

    public static void cleanHrmResourceCache() throws Exception {

        ResourceComInfo rc = new ResourceComInfo();

        rc.removeResourceCache();

    }

    /**
     *
     * 清除岗位缓存
     *
     * @throws Exception
     */

    public static void cleanJobTitlesCache() throws Exception{

        JobTitlesComInfo jobTitlesComInfo = new JobTitlesComInfo();

        jobTitlesComInfo.removeJobTitlesCache();

    }

    /**
     *
     * 清除分部缓存
     *
     * @throws Exception
     */

    public static void cleanSubCompanyCache() throws Exception{

        SubCompanyComInfo subCompanyComInfo = new SubCompanyComInfo();

        subCompanyComInfo.removeCompanyCache();

    }

    /**
     *
     * 清除部门缓存
     *
     */

    public static void cleanDepartmentCache(){

        DepartmentComInfo departmentComInfo = new DepartmentComInfo();

        departmentComInfo.removeCompanyCache();

    }

}
