/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enkriptor;

/**
 *
 * @author ramandika.pranamulia
 */
import java.util.*;
public class Enkriptor {

    /**
     * @param s
     * @param keyword
     * @return 
     */
    public static String vigenere_cipher(String s, String keyword){//Sesuai dengan input
        StringBuilder chiperText=new StringBuilder();
        s=s.toUpperCase();
        keyword=keyword.toUpperCase();
        int iterator=0;
        char temp;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)>=65 && s.charAt(i)<=122){
                temp=(char)(((int) s.charAt(i)+(int)keyword.charAt(iterator))-65);
                if(temp>=91) temp=(char)((int)temp%91+65);
                iterator++;
            }
            else temp=s.charAt(i);
            chiperText.append(temp);
            if(iterator==keyword.length()) iterator=0;
        }
        return chiperText.toString();
    }
    
    public static String vigenere_cipher_remove_space(String s,String keyword){
        StringBuilder str=new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=' ') str.append(s.charAt(i));
        }
        String result=vigenere_cipher(str.toString(),keyword);
        return result;
    }
    
    public static String vigenere_cipher_group_of_five(String s,String keyword){
        String result=vigenere_cipher_remove_space(s,keyword);
        StringBuilder str=new StringBuilder();
        for(int i=0;i<result.length();i++){
            if((i+1)%5==1 && i!=0) str.append(' ');
            str.append(result.charAt(i));
        }
        return str.toString();
    }
    
    public static String decrypt_vigenere(String s,String keyword){
        StringBuilder str=new StringBuilder();
        int idxKeyword=0;
        int plainChar;
        keyword=keyword.toUpperCase();
        for(int i=0;i<s.length();i++){
            plainChar=(int)s.charAt(i);
            if(s.charAt(i)>=65 && s.charAt(i)<=122){
                plainChar-=(int)keyword.charAt(idxKeyword);
                if(plainChar<0) plainChar=91+plainChar;
                else plainChar+=65;
            }
            else idxKeyword--;
            str.append((char)plainChar);
            if(idxKeyword==(keyword.length()-1)) idxKeyword=-1;
            idxKeyword++;
        }
        return str.toString();
    }
    
    public static String decrypt_vigenere_autokey(String s,String keyword){
        StringBuilder result=new StringBuilder();
        StringBuilder key=new StringBuilder();
        s=s.toUpperCase();
        keyword=keyword.toUpperCase();
        int iteratorKey=0;int iteratorS=0;
        while(iteratorS<s.length()){
            while(iteratorKey<keyword.length() && iteratorS<s.length()){//Ganti keyword
                int temp=(int)s.charAt(iteratorS);
                if(s.charAt(iteratorS)>=65 && s.charAt(iteratorS)<=90){
                    temp=(int)s.charAt(iteratorS)-(int)keyword.charAt(iteratorKey);
                    if(temp<0) temp+=91;
                    else temp+=65;
                    key.append((char)temp);
                    iteratorKey++;
                }
                result.append((char)temp);
                iteratorS++;
            }
            keyword=key.toString(); key.setLength(0);iteratorKey=0;
        }
        return result.toString();
    }
    
    public static String decrypt_vigenere_ASCII_autokey(String s,String keyword){
        StringBuilder result=new StringBuilder();
        StringBuilder key=new StringBuilder();
        int iteratorKey=0,iteratorS=0;
        while(iteratorS<s.length()){
            while(iteratorKey<keyword.length() && iteratorS<s.length()){//Ganti keyword
                int temp=(int)s.charAt(iteratorS)-(int)keyword.charAt(iteratorKey);
                if(temp<0) temp+=256;
                key.append((char)temp);
                result.append((char)temp);
                iteratorKey++;iteratorS++;
            }
            keyword=key.toString(); key.setLength(0);iteratorKey=0;
        }
        return result.toString();
    }
    
    public static String vigenere_cipher_ASCII(String s,String keyword){
        StringBuilder chiperText=new StringBuilder();
        int iterator=0;
        for(int i=0;i<s.length();i++){
            char toConvert=s.charAt(i);
            int charChiper=((int)toConvert+(int)keyword.charAt(iterator));
            charChiper=(charChiper%256);
            char result=(char) charChiper;
            chiperText.append(result);
            if(iterator==(keyword.length()-1)) iterator=-1;
            iterator++;
        }
        String str=chiperText.toString();
        return str;        
    }
    public static  String vigenere_cipher_ASCII_remove_space(String s,String keyword){
        StringBuilder str=new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=' ') str.append(s.charAt(i));
        }
        String result=vigenere_cipher_ASCII(str.toString(),keyword);
        return result;
    }
    
    public static String decrypt_ASCII(String s,String keyword){
        StringBuilder str=new StringBuilder();
        int iterator=0;
        for(int i=0;i<s.length();i++){
            int temp=s.charAt(i)-keyword.charAt(iterator);
            if(temp<0) temp+=256;
            str.append((char)temp);
            iterator++;
            if(iterator==keyword.length()) iterator=0;
        }

        return str.toString();
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan=new Scanner(System.in);
        String plainText=scan.nextLine();
        String keyword=scan.nextLine();
    }
    
}
