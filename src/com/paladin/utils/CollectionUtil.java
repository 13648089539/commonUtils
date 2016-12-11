package com.paladin.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CollectionUtil {

	public static <T> boolean isEmpty(Collection<T> collection){
		return null == collection || collection.size()<=0;
	}
	
	public static boolean isEmpty(Map<Object, Object> map){
		return null == map || map.isEmpty();
	}
	
	public static boolean isEmpty(Object[] objects){
		return null == objects || objects.length <=0;
	}
	
	/**
	 * ����ȥ��
	 * @param arg
	 * @return ��<b>arg</b>���շ���null;��t����ȥ�غ���¼���
	 */
	public static <T> ArrayList<T> distinctList(ArrayList<T> arg){
		ArrayList<T> distinct = new ArrayList<T>();
		if(null != arg){
			distinct = new ArrayList<T>();
			distinct.addAll(arg);
			for(int i = 0; i < distinct.size(); i++){
				T t1 = distinct.get(i);
				for(int j = i+1; j < distinct.size(); j++){
					T t2 = distinct.get(j);
					if(t1.equals(t2)){
						distinct.remove(j);
						j--;
					}
				}
			}
		}
		return distinct;
	}
	
	
	/**
	 * ��ȡ����
	 * @param arg1
	 * @param arg2
	 * @return ��ɂ����Ͼ���Մt����null;������һ�����Մt���ذ�������һ���������ݵ��¼���;���߾������Մt���ذ������߹�ͬԪ�ص��¼���
	 */
	public static <T> ArrayList<T> unionList(ArrayList<T> arg1,ArrayList<T> arg2){
		ArrayList<T> union = new ArrayList<T>();
		if(null != arg1) union.addAll(arg1);
		if(null != arg2) union.addAll(arg2);
		return distinctList(union);
	}
	
	/**
	 * ��ȡ����
	 * @param arg1
	 * @param arg2
	 * @return  ����
	 */
	public static <T> ArrayList<T> intersectionList(ArrayList<T> arg1,ArrayList<T> arg2){
		ArrayList<T> intersection = null;
		if(null != arg1 && null != arg2){
			intersection = new ArrayList<T>();
			for(T t1 : arg1){
				for(T t2 : arg2){
					if(t1.equals(t2)){
						intersection.add(t1);
					}
				}
			}
		}
		return distinctList(intersection);
	}
}
