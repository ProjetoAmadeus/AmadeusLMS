/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_sdmm.dao;

/**
 * @author armando
 *
 */
/*
 * Thumbnail.java
 *
 * Created on 6 de Julho de 2007, 15:17
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.io.BufferedReader; 
import java.io.BufferedWriter; 
import java.io.InputStreamReader; 
import java.io.OutputStreamWriter;
import java.io.OutputStream; 
import br.ufpe.cin.amadeus.amadeus_sdmm.general.Sdmm;

/**
 *
 * @author ANTONIO/ARMANDO
 */
public class Thumbnail {
    
    /** Creates a new instance of Thumbnail */
    public Thumbnail() {
    }

    /**
	 * Uses a Runtime.exec()to use imagemagick to perform the given conversion
	 * operation. Returns true on success, false on failure. Does not check if
	 * either file exists.
	 *
	 * @param in - InputFile that represents
	 * @param out - OutputFile that represents a new thumbnail
	 * @param width - width of thumbnail to be generated
         * @param height - height of thumbnail to be generated
	 * @param quality - quality of new thumbnail
	 * @return exec - return true if is the command is sucessful
	 */
    public static boolean convert(File in, File out, int width, int height, int quality) {       
        ArrayList command = new ArrayList(10);
        try{
            if (quality < 0 || quality > 100) {
                quality = 75;
            }            
            // note: CONVERT_PROG is a class variable that stores the location of ImageMagick’s convert command
            // it might be something like “/usr/local/magick/bin/convert” or something else, depending on where you installed it.
            command.add("convert");
            command.add("-geometry");
            command.add(width + "x" + height);
            command.add("-quality");
            command.add("" + quality);
            command.add(in.getAbsolutePath());
            command.add(out.getAbsolutePath());
            System.out.println(command);
        }
        catch(Exception e){
            System.out.println("Type of exception catched: "+e.toString());
            System.out.println("Exception in method convert from MultimediaManipulator :"+e.getMessage());            
            //pode dá erro no in - inputfile
            //pode dá erro no out - outputfile
            //pode passar parametros invalidos para width, height ou quality
        }
	return exec((String[])command.toArray(new String[1]));
    }
            
    /**
	 * Uses a Runtime.exec()to use ffmpeg to perform the given conversion
	 * operation to create a thumbnail with witdh and high dimensions. 
         * As a result, the operation creates a new jpeg file contend the first frame of the video file.
         * Returns true on success, false on failure. Does not check if
	 * either file exists.
         * Example of command: ffmpeg -i videoname.mpeg -s 90x90 -vframes 1 firstframe%d.jpeg 
	 *
	 * @param in - is the videofile to operation
         * @param width - is the width of thumbnail
         * @param hight - is the hight of thumbnail
         * @param filePath - is the path of thumbnail created by operation
         * @param id -is the identification number of thumbnail
	 * @return exec - returns true if the command is sucessful
	 */
    public static boolean videoGetFirstFrame(File in, int width, int hight, String filePath, int id){
        ArrayList command = new ArrayList(10);
        try{
            command.add("ffmpeg");
            command.add("-i");
            command.add(in.getAbsolutePath());
            command.add("-s");
            command.add(width+"x"+hight);
            command.add("-vframes");
            command.add("1");
            command.add(filePath+"thumbnail"+"%d"+"-"+id+".jpg");
            System.out.println(command);
        }catch(Exception e){
            System.out.println("Type of exception catched: "+e.toString());
            System.out.println("Exception in method videoGetFirstFrame from MultimediaManipulator :"+e.getMessage());                                    
            //pode dá erro no in - inputfile
            //pode dá erro no thumbnailName - invalide name to thumbnail generated
            //pode passar parametros invalidos para width, height
        }
        return exec((String[])command.toArray(new String[1]));
    }   
    
    	/**
	 * Tries to exec the command, waits for it to finsih, logs errors if exit
	 * status is nonzero, and returns true if exit status is 0 (success).
	 * audio "mp3;MP3#mp4;MPEG-4 AAC#ra;Real Audio#wma;WMA"
         * video "avi;AVI#mp1;MPEG-1#mp2;MPEG-2#mp4;MPEG-4#ogg;OGG#rm;Real Video#wmv;WMV;flv#FLV"
         *
	 * @param command - the command to be performed by ffmpeg process
	 * @return true if the command is successful
	 */
    private static boolean exec(String[] command) {
        Process proc; //the process created by program to manipulate ffmpeg external program process
        BufferedReader bInputStream = null;  //buffer to store the input from ffmpeg process 
        BufferedWriter bOutputStream = null; //buffer to store the output from ffmpeg process 
        BufferedReader bErrorStream = null;  //buffer to store the error from ffmpeg process

	try {
            proc = Runtime.getRuntime().exec(command);
            
            bErrorStream = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            String s;
            String sError="";
            //block to store the string generated by error ffmpeg process
            while ((s = bErrorStream.readLine())!= null) {
                sError = sError + s;
            }
            bErrorStream.close();
            
            bInputStream = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String s2;
            String sInput="";
            while ((s2 = bInputStream.readLine())!= null) {
                sInput = sInput + s2;
            }
            bInputStream.close();
            
            bOutputStream = new BufferedWriter(new OutputStreamWriter(proc.getOutputStream()));
            bOutputStream.close();
            
            proc.getInputStream().close();
            proc.getOutputStream().close();
            proc.getErrorStream().close();
        } catch (IOException e) {
		System.out.println("IOException while trying to execute " + command);
                Thread.currentThread().interrupt(); 
		return false;
	} catch (Exception e){
                System.out.println("Exception in "+e.toString());
                System.out.println("Exception in Exec from MultimediaManipulator : "+e.getMessage());
		System.out.println("Exception while trying to execute " + command);
                Thread.currentThread().interrupt(); 
		return false;            
        }	
	int exitStatus;
	while (true) {
		try {
			exitStatus = proc.waitFor();                        
			break;
		} catch (java.lang.InterruptedException e) {
			System.out.println("Interrupted: Ignoring and waiting");
                        Thread.currentThread().interrupt();                         
		}
	}
	if (exitStatus != 0) {
		System.out.println("Error executing command: " + exitStatus);
	}
	return (exitStatus == 0);
    }                    
}