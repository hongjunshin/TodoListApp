package com.todo.service;

import java.io.Writer;
import java.io.*;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, category,desc, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print(
				 "========== 항목 추가\n"
				+ "제목을 입력하시오 : ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("이미 같은 제목이 있습니다");
			return;
		}
		System.out.print("카테고리를 입력하시오 : ");
		category = sc.next().trim();
		sc.nextLine();
		System.out.print("내용을 입력하시오 : "); 
		desc = sc.nextLine().trim();
		System.out.print("마감일자를 입력하시오 : ");
		due_date = sc.next().trim();
		
		TodoItem t = new TodoItem(title, category,desc,due_date);
		list.addItem(t);
		System.out.println("추가되었습니다."); 
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print(
				"========== 항목 제거\n"
				+ "제거할 항목의 번호를 쓰시오 : "
				);
		int number = sc.nextInt();
		if (l.size()<number) {
			System.out.println("항목 번호 중에 "+ number + "이 없습니다");
			return;
		}
		TodoItem item = l.get(number-1);
		System.out.println((l.indexOf(item)+1)+". "+item.toString());
		System.out.print("삭제하시겠습니까?(y/n)");
		String question = sc.next().trim();
		if(question.charAt(0) == 'y') {
			l.deleteItem(item);
			System.out.println("삭제되었습니다."); 
		}
			
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print(
				"========== 항목 수정\n"
				+ "수정할 항목의 번호를 입력하시오 : ");
		int number = sc.nextInt();
		
		if (l.size()<number) {
			System.out.println("항목 번호 중에 "+ number + "이 없습니다");
			return;
		}
		TodoItem item = l.get(number-1);
		System.out.println((l.indexOf(item)+1)+". "+item.toString());

		System.out.print("새로운 제목 : ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("같은 제목을 가진 항목이 있습니다");
			return;
		}
		System.out.print("새로운 카테고리 : ");
		String category = sc.next().trim();
		sc.nextLine();
		System.out.print("새로운 내용 : ");
		String new_description = sc.nextLine().trim();
		System.out.print("새로운 마감일자 : ");
		String due_date = sc.next().trim();
		
		l.deleteItem(item);
		TodoItem t = new TodoItem(new_title,category, new_description,due_date);
		l.addItem(t);
		System.out.println("항목이 수정되었습니다.");
			
		
       
	}

	public static void listAll(TodoList l) {
		System.out.println("[전체 목록, 총 "+ l.size() +"개]");
		for (TodoItem item : l.getList()) {
			System.out.println((l.indexOf(item)+1)+". "+item.toString());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			System.out.println("항목 정보를 "+filename+"에 저장했습니다!");
			
		}
		catch (FileNotFoundException e){
			System.out.println("파일을 찾을 수 없습니다");
		}
		catch (IOException e){
			System.out.println("파일 오류 발생!!");
		}
	}
	
	public static void find(TodoList l, String keyword) {
		for (TodoItem item : l.getList()) {
			if(item.toString().contains(keyword)) {
				System.out.println((l.indexOf(item)+1)+". "+item.toString());
			}
		}	
	}
	public static void find_cate(TodoList l, String keyword) {
		for (TodoItem item : l.getList()) {
			if(item.getCategory().contains(keyword)) {
				System.out.println((l.indexOf(item)+1)+". "+item.toString());
			}
		}	
	}
	
	public static void listCate(TodoList l) {
		int number = 0;
		HashSet<String> cateset = new HashSet<String>();
		for (TodoItem item : l.getList()) {
			cateset.add(item.getCategory());
		}
		for(Object obj : cateset.toArray()) {
			String category = (String) obj;
			System.out.print(category+"   ");
			number++;
		}
		System.out.println("\n총 "+number+"개의 카테고리가 등록되어 있습니다.");
	}
	
	
	public static void loadList(TodoList l, String filename) {
		try {
		BufferedReader br = new BufferedReader (new FileReader(filename));
		
		String oneLine;
		while((oneLine = br.readLine())!=null) {
			StringTokenizer st = new StringTokenizer(oneLine,"##");
			String title = st.nextToken();
			String category = st.nextToken();
			String desc = st.nextToken();
			String due_date = st.nextToken();
			String current_date = st.nextToken();
			
			TodoItem t = new TodoItem(title, category,desc,due_date);
			t.setCurrent_date(current_date);
			l.addItem(t);
		}
		br.close();
		System.out.println("항목 정보를 로딩했습니다.");
		}
		catch (FileNotFoundException e){
			System.out.println("파일이 없습니다.");
		}
		catch (IOException e){
			System.out.println("파일 오류 발생!!");
		}
	
	}
}
