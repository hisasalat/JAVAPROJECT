/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphapplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JAV
 */
public class DataClass {
    public Integer MAXVALUE;
    public List<Integer> DataList;
    public String TITLE;
    public String XLABEL;
    public String YLABEL;
    public Integer START;
    public Integer INTERVAL;
    
    public DataClass()
    {
        
    }
    
     public void setTITLE(String value)
    {
        this.TITLE=value;
    }
    public String getTITLE()
    {
        return this.TITLE;
    }
    
    public void setXLABEL(String value)
    {
        this.XLABEL=value;
    }
    public String getXLABEL()
    {
        return this.XLABEL;
    }
    
    public void setYLABEL(String value)
    {
        this.YLABEL=value;
    }
    public String getYLABEL()
    {
        return this.YLABEL;
    }
    
    public void setStart(Integer value)
    {
        this.START=value;
    }
    public int getStart()
    {
        return this.START;
    }
    
    
    public void setInterval(Integer value)
    {
        this.INTERVAL=value;
    }
    public int getInterval()
    {
        return this.INTERVAL;
    }
    
    public void setMaxValue(Integer value)
    {
        this.MAXVALUE=value;
    }
    public int getMaxVALUE()
    {
        return this.MAXVALUE;
    }
    
    public void setDataList(List<Integer> value)
    {
        this.DataList=value;
    }
    
    public List<Integer> getDataLIST()
    {
        return this.DataList;
    }
    public Integer FinMaxValue(List<Integer> values)
    {
        Integer max=values.get(0);
        for(int i=0;i<values.size();i++)
        {
            if(max<values.get(i))
            {
                max=values.get(i);
            }
        }
        return max;
    }
    public List<Integer> SortList(List<Integer> values)
    {
        Collections.sort(values);
        return values;
    }
    
   public Integer FindAverage(List<Integer> values)
    {
        Integer average=0;
        for(int i=0;i<values.size();i++)
        {
            average+=values.get(i);
        }
        average=average/values.size();
        return average;
    }
    public void ReadTextFile(String FileName)
    {
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream(FileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            try {
                //Read File Line By Line
                while ((strLine = br.readLine()) != null)   {
                    
                    if(strLine.contains("Title"))
                    {
                        String[] array=strLine.split(":");
                        setTITLE(array[1]);
                    }
                    else if(strLine.contains("Xlabel"))
                    {
                        String[] array=strLine.split(":");
                        setXLABEL(array[1]);
                    }
                    else if(strLine.contains("Ylabel"))
                    {
                        String[] array=strLine.split(":");
                        setYLABEL(array[1]);
                    }
                    else if(strLine.contains("start"))
                    {
                        String[] array=strLine.split(":");
                        try
                        {
                        setStart(Integer.parseInt(array[1].replace(" ", "")));
                        }
                        catch(Exception ex)
                        {
                            setStart(new Double(array[1].replace(" ", "")).intValue());
                        }
                    }
                    else if(strLine.contains("interval"))
                    {
                        String[] array=strLine.split(":");
                        try
                        {
                        setInterval(Integer.parseInt(array[1].replace(" ", "")));
                        }
                        catch(Exception ex)
                        {
                        setInterval(new Double(array[1].replace(" ", "")).intValue());
                        }
                    }
                    else
                    {
                        List<Integer> temp = new ArrayList<Integer>();
                        String[] arr=strLine.split(",");
                        for(int i=0;i<arr.length;i++)
                        {
                            try
                            {
                            Integer nn=Integer.parseInt(arr[i].replace(" ", ""));
                            temp.add(nn);
                            }
                            catch(Exception ex)
                            {
                                    Integer nn=new Double(arr[i].replace(" ", "")).intValue();
                                    temp.add((int)nn);
                            }
                        }
                        setDataList(temp);
                    }
                }          
            } catch (IOException ex) {
                Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fstream.close();
            } catch (IOException ex) {
                Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
