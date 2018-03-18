package com.example.asus.demo.utils;

import com.example.asus.demo.configs.Letter;
import com.example.asus.demo.configs.Type;
import com.example.asus.demo.entity.ContractorEntity;
import com.github.promeg.pinyinhelper.Pinyin;

import org.w3c.dom.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.example.asus.demo.configs.Letter.LETTERCHAR;


public class SortListUtil {
    /**
     * 对List进行排序
     * @param list
     * @return
     */
    public static List<ContractorEntity> sortList(List<ContractorEntity> list){
        List<ContractorEntity> entityList = new ArrayList<>();
        entityList.addAll(list);
         Comparator<ContractorEntity> characterComparator = new Comparator<ContractorEntity>() {
             @Override
             public int compare(ContractorEntity contractorEntity, ContractorEntity t1) {


                 return Comparate(contractorEntity,t1);
             }
         };

//        list.sort(characterComparator);
        Collections.sort(list,characterComparator);

        return entityList;
    }


    /**
     * 定义排序的算法
     * @param entity
     * @param nextEntity
     * @return
     */

    public static int Comparate(ContractorEntity entity,ContractorEntity nextEntity){
        int result = 0;
        String name = Pinyin.toPinyin(entity.getName(),"");
        String nextName = Pinyin.toPinyin(nextEntity.getName(),"");
        int nameIndex=0;
        int nextNameIndex=0;
        int nameLength = name.length();
        int nextNameLength = nextName.length();
        char nameChar  = name.charAt(0);
        char nextNameChar = nextName.charAt(0);
        if ((nameChar<='Z'&&nameChar>='A')&&!(nextNameChar<='Z'&&nextNameChar>='A')){
            return -1;
        }else if (!(nameChar<='Z'&&nameChar>='A')&&(nextNameChar<='Z'&&nextNameChar>='A')){
            return 1;
        }

        while ((nameIndex<nameLength)&&(nextNameIndex<nextNameLength)){

              if (name.charAt(nameIndex)<nextName.charAt(nextNameIndex)){
                  result = -1;
                  break;
              }else if (name.charAt(nameIndex)>nextName.charAt(nextNameIndex)){
                  result = 1;
                  break;
              }else {
                  result = 0;
                  nameIndex++;
                  nextNameIndex++;

              }

              if (nextNameIndex==nextNameLength){
                  result = 1;
              }else if (nameIndex==nameLength){
                  result = -1;
              }else {
                  result = 0;
              }

        }

         return result;
    }


    /**
     * 向联系人List中添加字母
     * @param contractorEntities
     * @param entityList
     * @return
     */
    public static List<ContractorEntity> addDividerLetter(List<ContractorEntity> contractorEntities,List<ContractorEntity> entityList){

        boolean isAddChar = false;
        for (int i=1;i< LETTERCHAR.length;i++){
            for (int j=0;j<entityList.size();j++){
                char  name = Pinyin.toPinyin(entityList.get(j).getName(),"").charAt(0);
                if (name==LETTERCHAR[i]){
                    ContractorEntity entity = new ContractorEntity();
                    entity.setName(Letter.LETTER[i]);
                    entity.setType(Type.LETTER);
                    contractorEntities.add(entity);
                    break;
                }else if (name>'Z'||name<'A'&&!isAddChar){
                    ContractorEntity entity =new ContractorEntity();
                    entity.setName("#");
                    entity.setType(Type.LETTER);
                    contractorEntities.add(entity);
                    isAddChar =true;

                }
            }
        }
        return contractorEntities;
    }









}
