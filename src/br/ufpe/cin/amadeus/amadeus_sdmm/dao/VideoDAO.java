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
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import br.ufpe.cin.amadeus.amadeus_sdmm.general.Sdmm;
//package javaapplication1;


/*
 * ImagemDAO.java
 *
 * Created on 1 de Julho de 2007, 14:00
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author ANTONIO/ARMANDO
 * TODO: Criar a classe DAO
 */
public class VideoDAO {
    
    Dao dao = new Dao();
    
    /** Creates a new instance of ImagemDAO */
    public VideoDAO() {

    }

    public int getMaxId(){
        int id = 0;
        //String comando = "select id from videos where id = (select max(id) from videos)";
        String comando = "select nextval('videos_pk_id_seq') as idf";
	this.dao.getConnection();
	ResultSet rs = this.dao.executaConsultaSQL(comando);
	try {
            if(rs.next()){
                id = Integer.parseInt(rs.getString("idf"));
            }
        } catch (SQLException e) {
            System.out.println("Exception em getMaxId em VideoDAO"+e.getMessage());
	}
	this.dao.closeConnection();
	return id;	
    }
    
    synchronized public void insertVideo(Video video){
        try{
        //TODO: Colocar thumbnail...
	String sql = "INSERT INTO videos (id,description,name,author,tags,video,datemodification,license,"+
                "width,height,thumbnail,length,dateinsertion,extension,bitrate,framerate,"+
                "duration,standard) VALUES ("+video.getId()+",'"+video.getDescription()+"','"+
                video.getName()+"','"+video.getAuthor()+"','"+video.getTags()+"',lo_import('"+video.getVideo()+"'),"+
                video.getDateModification()+","+
                video.getLicense()+","+video.getWidth()+","+video.getHeight()+",lo_import('"+video.getThumbnail()+"')," +
                video.getLength()+",'"+video.getDateinsertion()+"','"+video.getExtension()+"',"+
                video.getBitrate()+","+video.getFramerate()+",'"+video.getDuration()+"','"+
                video.getStandard()+"')";
	this.dao.getConnection();
        this.dao.executaComandoSQL(sql);
	this.dao.closeConnection();
        }catch(Exception e){
            System.out.println("Exception em InserVideo em videoDAO"+e.getMessage());
            e.printStackTrace();            
        }
    }
    
    synchronized public void updateVideo(String description,String name,String author,String tags,
            char license, int id){
        try{           
	String sql = "update videos set description = '"+description+"', name = '"+
                name+"', author = '"+author+"',tags = '"+tags+"',"+
                "license = "+license+" where id = "+id;
	this.dao.getConnection();
        this.dao.executaComandoSQL(sql);
	this.dao.closeConnection();
        }catch(Exception e){
            System.out.println("Exception em updateVideo em VideoDAO: "+e.getMessage());
            e.printStackTrace();            
        }
    }
    
    
    //public void loadVideo(int id, String extension){
    synchronized public void loadVideo(Video video, String extension){
        String directoryVideos = Sdmm.directoryVideos;
        try{
            //File file = new File("C:/Program Files/Tomcat 5.0/webapps/ROOT/prototipo4/videos/video"+video.getId()+"."+extension);
            File file = new File(directoryVideos+"video"+video.getId()+"."+extension);
            //Video video = this.get(id);

            //if(video.getLength() != file.length() || video.getDateModification() != file.lastModified()){        
            if(video.getLength() != file.length()){
                /*String sql = "SELECT lo_export(videos.video, 'C:/Program Files/Tomcat 5.0/webapps/ROOT/prototipo4/videos/"
                        +"video"+video.getId()+"."+extension+"') FROM videos WHERE id = "+video.getId();*/
                String sql = "SELECT lo_export(videos.video, '"+directoryVideos+"video"+video.getId()+"."+extension+"') FROM videos WHERE id = "+video.getId();
                this.dao.getConnection();
                this.dao.executaComandoSQL(sql);
                this.dao.closeConnection();

                //Após fz o lo_export é necessário corrigir a data de modificação do arquivo exportado, já que como ele cria um novo
                //arquivo a data de modificação do exportado sempre seria diferente da do banco e o algoritmo bugaria...
                //File fileLoaded = new File("C:/Program Files/Tomcat 5.0/webapps/ROOT/prototipo4/videos/video"+video.getId()+"."+extension);
                File fileLoaded = new File(directoryVideos+"videos"+video.getId()+"."+extension);
                fileLoaded.setLastModified(video.getDateModification());
            }
        }catch(Exception e){
            System.out.println("Exception em loadVideo em VideoDAO: "+e.getMessage());
            e.printStackTrace();                        
        }
    }

    //public void loadThumbnail(int id){
    synchronized void loadThumbnail(Video video){    
        String directoryVideos = Sdmm.directoryVideos;
        try{
            //File file = new File("C:/Program Files/Tomcat 5.0/webapps/ROOT/prototipo4/videos/video"+video.getId()+"."+extension);
            File file = new File(directoryVideos+"thumbnail1-"+video.getId()+".jpg");
            
            //Não guardamos na tabela de video o tamanho do thumnail então apenas comparando a data de modificação já resolve
            if(video.getDateModification() != file.lastModified()){
            /*String sql = "SELECT lo_export(images.thumbnail, 'C:/Program Files/Tomcat 5.0/webapps/ROOT/prototipo4/images/"
                +"thumbnail"+image.getId()+".jpg') FROM images WHERE id = "+image.getId();*/
                
                String sql = "SELECT lo_export(videos.thumbnail, '"+directoryVideos+"thumbnail1-"+video.getId()+".jpg') FROM videos WHERE id = "+video.getId();
                
                this.dao.getConnection();
                this.dao.executaComandoSQL(sql);
                this.dao.closeConnection();
                
                //Após fz o lo_export é necessário corrigir a data de modificação do arquivo exportado, já que como ele cria um novo
                //arquivo a data de modificação do exportado sempre seria diferente da do banco e o algoritmo bugaria...
                File fileLoaded = new File(directoryVideos+"thumbnail1-"+video.getId()+".jpg");
                fileLoaded.setLastModified(video.getDateModification());
            }
        } catch(Exception e){
            System.out.println("Exception em loadThumbnail em VideoDAO: "+e.getMessage());
            e.printStackTrace();
        }        
    }
    
   
    synchronized public void deleteVideo(int id){
        String videoOID="";
        try{
            String sqlSearch = "SELECT video FROM videos WHERE id = '" + id + "'";            
            String sqlDelete = "DELETE FROM videos WHERE id = '" + id + "'";
            this.dao.getConnection();
            ResultSet rs = this.dao.executaConsultaSQL(sqlSearch);
            try{
                if (rs.next()){
                    videoOID = rs.getString("video");
                }else{
                    videoOID = null;
                }
            }catch(Exception e1){
                System.out.println("Exception em deleteVideo em VideoDAO :"+e1.getMessage());    
            }
            String sqlUnlinkVideo = "SELECT lo_unlink("+videoOID+")";  
            ResultSet rsUnlinkVideo = this.dao.executaConsultaSQL(sqlUnlinkVideo);
            this.dao.executaComandoSQL(sqlDelete);
            this.dao.closeConnection();
        }catch(Exception e){
            System.out.println("Exception em deleteVideo em VideoDAO :"+e.getMessage());
            e.printStackTrace();            
        }        
    }
    
    
    public Video get(int id){
        Video video = null;
   
        String sql = "select * from videos where id = "+id;
        
        this.dao.getConnection();
	ResultSet rs = this.dao.executaConsultaSQL(sql);
	
        try {
            if(rs.next()){
                video = new Video(rs.getInt("id"),rs.getString("description"),rs.getString("name"),rs.getString("author"),
                        rs.getString("tags"),"",rs.getLong("datemodification"),rs.getString("license").charAt(0),rs.getInt("width"),rs.getInt("height"),
                        "",rs.getLong("length"),rs.getDate("dateinsertion"),rs.getString("extension"),rs.getInt("bitrate"),
                        rs.getDouble("framerate"),rs.getString("duration"),rs.getString("standard"));
                
                video.setVideo("video"+video.getId()+"."+video.getExtension());

            }
	} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
	}
				
	this.dao.closeConnection();
	return video;
        
    }
    
   
    public Vector search(String kind, String value,int begin){
        int offset = begin-1;
        Vector retorno = new Vector();
        String sql = "select * from videos where "+kind+" LIKE '%" + value +  "%' ORDER BY id ASC LIMIT 10 OFFSET "+offset;
        this.dao.getConnection();
	ResultSet rs = this.dao.executaConsultaSQL(sql);
	
        try {
            while(rs.next()){
                Video video = new Video(rs.getInt("id"),rs.getString("description"),rs.getString("name"),rs.getString("author"),
                        rs.getString("tags"),"",rs.getLong("datemodification"),rs.getString("license").charAt(0),rs.getInt("width"),rs.getInt("height"),
                        "",rs.getLong("length"),rs.getDate("dateinsertion"),rs.getString("extension"),rs.getInt("bitrate"),
                        rs.getDouble("framerate"),rs.getString("duration"),rs.getString("standard"));
                
                video.setVideo("video"+video.getId()+"."+video.getExtension());
                
		retorno.add(video);
            }
	} catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Excecao no search em VideoDAO"+e.getMessage());
            e.printStackTrace();
	}				
	this.dao.closeConnection();
	return retorno;        
    }
    
    public Vector searchAdvanced(String searchType, String value1, String value2, int begin){
        int offset = begin-1;
        Vector retorno = new Vector();
        String sql = "";
        if(searchType.equals("period")){
            sql = "select * from videos where dateinsertion >= '"+value1+"' and dateinsertion <= '"+value2+
                    "' ORDER BY id ASC LIMIT 10 OFFSET "+offset;
        }else if(searchType.equals("resolution")){
            sql = "select * from videos where width = "+value1+" and height = "+value2+
                    " ORDER BY id ASC LIMIT 10 OFFSET "+offset;
        }
        this.dao.getConnection();
	ResultSet rs = this.dao.executaConsultaSQL(sql);
	
        try {
            while(rs.next()){
                Video video = new Video(rs.getInt("id"),rs.getString("description"),rs.getString("name"),rs.getString("author"),
                        rs.getString("tags"),"",rs.getLong("datemodification"),rs.getString("license").charAt(0),rs.getInt("width"),rs.getInt("height"),
                        "",rs.getLong("length"),rs.getDate("dateinsertion"),rs.getString("extension"),rs.getInt("bitrate"),
                        rs.getDouble("framerate"),rs.getString("duration"),rs.getString("standard"));
                
                video.setVideo("video"+video.getId()+"."+video.getExtension());
                
		retorno.add(video);
            }
	} catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Excecao no SearchAdvanced em VideoDao"+e.getMessage());
            e.printStackTrace();
	}
				
	this.dao.closeConnection();
	return retorno;
        
    }
        
    public int getAmount(String kind, String value){
        int ret = 0;
   
        String sql = "select count(*) from videos where "+kind+" like '%"+value+"%'";
        
        this.dao.getConnection();
	ResultSet rs = this.dao.executaConsultaSQL(sql);
	
        try {
            if(rs.next()){
                ret = rs.getInt("count");
            }
	} catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Excecao no getAmount do VideoDAO"+e.getMessage());
            e.printStackTrace();
	}				
	this.dao.closeConnection();
	return ret;        
    }
    
    public int getAmountAdvanced(String searchType, String value1, String value2){
        int ret = 0;
   
        //String sql = "select count(*) from images where "+kind+" like '%"+value+"%'";
        String sql = "";
        if(searchType.equals("period")){
            sql = "select count(*) from videos where dateinsertion >= '"+value1+"' and dateinsertion <= '"+value2+"'";
        }else if(searchType.equals("resolution")){
            sql = "select count(*) from videos where width = "+value1+" and height = "+value2;
        }
        this.dao.getConnection();
	ResultSet rs = this.dao.executaConsultaSQL(sql);
	
        try {
            if(rs.next()){
                ret = rs.getInt("count");
            }
	} catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Excecao no getAmountAdvanced do VideoDAO"+e.getMessage());
            e.printStackTrace();
	}
				
	this.dao.closeConnection();
	return ret;        
    }  
}