package com.wyl.gittest;

import com.wyl.gittest.common.ResourceUtil;

/**
 * Hello world!
 * 
 */
public class App 
{
    public static void main( String[] args )
    {
    	String testStr = ResourceUtil.getCustomResource("git.test");
    	System.out.println(testStr);
    	
    	System.out.println( "Hello World!" );
    	
    	System.out.println("最新提交修改？");
    	
    }
}
