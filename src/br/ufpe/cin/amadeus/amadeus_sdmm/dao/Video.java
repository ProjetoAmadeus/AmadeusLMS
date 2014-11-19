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
 * @author Armando Soares Sousa/Antonio Nascimento
 *
 */
/*
 * Video.java
 *
 * Created on 13 de Julho de 2007, 17:15
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.sql.Date;

public class Video {
    
    private int id;
    private String description;
    private String name;
    private String author;
    private String tags;
    private String video;
    private long datemodification;
    private char license;
    private int width;
    private int height;
    private String thumbnail;
    private long length;
    private Date dateinsertion;
    private String extension;
    private int bitrate;
    private double framerate;
    private String duration;
    private String standard;
    
    /** Creates a new instance of Video */
    public Video(int id,String description,String name,String author,String tags,
            String video,long datemodification, char license,int width,int height,String thumbnail,
            long length,Date dateinsertion,String extension,int bitrate,
            double framerate,String duration,String standard) {
        
        this.id = id;
        this.description = description;
        this.name = name;
        this.author = author;
        this.tags = tags;
        this.video = video;
        this.datemodification = datemodification;
        this.license = license;
        this.width = width;
        this.height = height;
        this.thumbnail = thumbnail;
        this.length = length;
        this.dateinsertion = dateinsertion;
        this.extension = extension;
        this.bitrate = bitrate;
        this.framerate = framerate;
        this.duration = duration;
        this.standard = standard;      
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
    
    public long getDateModification() {
        return datemodification;
    }
    
    public void setDateModification(long datemodification) {
        this.datemodification = datemodification;
    }    

    public char getLicense() {
        return license;
    }

    public void setLicense(char license) {
        this.license = license;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public Date getDateinsertion() {
        return dateinsertion;
    }

    public void setDateinsertion(Date dateinsertion) {
        this.dateinsertion = dateinsertion;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public double getFramerate() {
        return framerate;
    }

    public void setFramerate(double framerate) {
        this.framerate = framerate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }
    
}
