/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.domain.register;


import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;



public class Thumbnail {
	int width = 124; // Lagura da miniatura
	int height = 160; // Altuta da miniatura
	

	// M�todo para redimensionar imagens (criar thubmnails)
	public byte[] resize(byte[] img) throws Exception {
		BufferedImage biSrc = ImageIO.read(new ByteArrayInputStream(img));
		if (biSrc == null)
			throw new Exception("errors.photo");

        double thumbRatio = (double) width / (double) height;
        int imageWidth = biSrc.getWidth();
        int imageHeight = biSrc.getHeight();

        double imageRatio = (double) imageWidth / (double) imageHeight;

        if (thumbRatio < imageRatio) {
              height = (int) (width / imageRatio);
        } else {
              width = (int) (height * imageRatio);
        }
        
		AffineTransform at = new AffineTransform();
		at.scale((double)width/biSrc.getWidth(), (double)height/biSrc.getHeight());

		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		BufferedImage biDst = op.filter(biSrc, null);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(biDst, "png", baos);

		return baos.toByteArray();
	}

}