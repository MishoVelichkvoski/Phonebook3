/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook123;

/**
 *
 * @author misho.velichkovski
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Phonebook123 {

    public static ArrayList<Contact> contacts=new ArrayList<>();

  public static void main(String[] args) {
        
         
         String fileName="C:\\Users\\misho.velichkovski\\Documents\\NetBeansProjects\\phonebook123\\src\\phonebook123\\ContactList.txt";
         String[] singleContact;
           String patternString1 = "\\+3598[789][0-9]{7}";
           Pattern pattern1 = Pattern.compile(patternString1);
           String patternString2 = "08[789][0-9]{7}";
           Pattern pattern2 = Pattern.compile(patternString2);
           String patternString3 = "003598[789][0-9]{7}";
           Pattern pattern3 = Pattern.compile(patternString3);
     
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

			String line;
			while ((line = br.readLine()) != null) {
				                                singleContact=line.split(" ");
                                Contact c=new Contact(singleContact[0],singleContact[1], singleContact[2]);
                                contacts.add(c);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
                
                Iterator<Contact> itr = contacts.iterator();
               while(itr.hasNext())
               {    
                   Contact c=itr.next();
                   /*if(c.getNumber().length()!= 13 || c.getNumber().length()!= 10 || c.getNumber().length()!= 14 )
                   {
                       contacts.remove(c);
                   }
                   else 
                   {*/
                    Matcher matcher1 = pattern1.matcher(c.getNumber());
                    Matcher matcher2 = pattern2.matcher(c.getNumber());
                    Matcher matcher3 = pattern3.matcher(c.getNumber());
                   if( !(matcher1.matches() || matcher2.matches() || matcher3.matches()))
                   {
                       itr.remove();
                   }
                   
                   
                   //}
               }
               
                 for (Contact c : contacts)
               {    
                   
                   StringBuilder str = new StringBuilder(c.getNumber());
                   if(str.charAt(0)=='0' && str.charAt(1)=='8')
                   {
                       str.deleteCharAt(0);
                       str=str.insert(0,"+359");
                       c.setNumber(str.toString());
                   }
                   if(str.charAt(0)=='0' && str.charAt(1)=='0')
                   {
                       str.deleteCharAt(0);
                       str.deleteCharAt(0);
                       str=str.insert(0,"+");
                       c.setNumber(str.toString());
                   }
               
               }
               
                Phonebook123 pb = new Phonebook123();
                 pb.printMostSearched();
               
      }
  
  public String searchContact(String ct)
               {
                   for (Contact c : contacts )
                   {
                       if(c.getName().equals(ct))
                       {
                            c.beenSearched=c.beenSearched +1 ;
                           return c.printContact();
                          
                       }
                   }
                   return "ERROR";
               }
  
  public void deleteContact(String nme)
  { 
      for (Contact c : contacts )
                   {
                       if(c.getName().equals(nme))
                       {
                            contacts.remove(c);
                          
                       }
                   }
                  
      
  }
  
  public String findContact (String nme)
  { 
      for (Contact c : contacts)
      { 
          if(c.getName().equals(nme))
          {
              return c.getNumber();
          }
      }
      return "No contact with that name!";
  }
  
    public void printByName()
    {
        String[] list = new String[contacts.size()];
        for (int i = 0; i < list.length; i++) {
             list[i]=contacts.get(i).getName();
            
        }
        
        Arrays.sort(list);
        ArrayList<Contact> pom=new ArrayList<>();
        for(Contact c : contacts)
        {
                for (int i = 0; i < list.length; i++) {
                if(c.getName().equals(list[i]))
                   {
                    pom.add(c);
                  }
                
            }
        }
        for( Contact c : pom)
        {
            System.out.println(c.printContact());
        }
                
        }
    
    public void printMostSearched(){
        
        int[] list =new int[contacts.size()];
        for (int i = 0; i < list.length; i++) {
             list[i]=contacts.get(i).getBeenSearched();
            
        }
                Arrays.sort(list);
                ArrayList<Contact> pom=new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                
                    for( Contact c : contacts)
                    {
                           if(c.getBeenSearched()==list[i])
                               pom.add(c);
                    }
            
        }
                for( Contact c : pom)
        {
            System.out.println(c.printContact());
        }
    }
    }