package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("add - 항목 추가");
        System.out.println("del - 항복 제거");
        System.out.println("edit - 항목 수정");
        System.out.println("find 키워드 - 키워드 찾기");
        System.out.println("find_cate 키워드 - 카테고리에서 키워드 찾기");
        System.out.println("ls - 목록");
        System.out.println("ls_name_asc - 제목순으로 정렬");
        System.out.println("ls_name_desc - 제목역순으로 정렬");
        System.out.println("ls_date - 날짜순으로 정렬");
        System.out.println("ls_date_desc - 날짜역순으로 정렬");
        System.out.println("ls_cate - 카테고리 목록 출력");
        System.out.println("exit - 프로그램 종료");
    }
    public static void prompt() {
    	System.out.print ("\n입력하시오 : ");
    }
}
