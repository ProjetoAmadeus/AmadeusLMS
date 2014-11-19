/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
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
	

	// Método para redimensionar imagens (criar thubmnails)
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