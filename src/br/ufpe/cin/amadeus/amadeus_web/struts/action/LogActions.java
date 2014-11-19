/**
 * 
 */
package br.ufpe.cin.amadeus.amadeus_web.struts.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.tools.ant.types.CommandlineJava.SysProperties;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Log;

/**
 * @author Pedro
 *
 */
public class LogActions extends SystemActions {

	@Override
	protected Map<String, String> getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();
		
		//System.out.println("mapeamento solicitado");
		map.put("log.saveLog", "saveLog");
		map.put("log.saveLogViewMaterial", "saveLogViewMaterial");
		map.put("log.saveLogViewVideo", "saveLogViewVideo");
		
		return map;
	}
	
	public ActionForward saveLog(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{
		//System.out.println("metodo para salvar o log chamado.");
		if(SystemActions.isLoggedUser(request)) {
			
			//TODO - LOG - Partida em Jogo - OK
			Log log = SystemActions.getLogUser(request);
			log.setCodigo(Log.LOG_CODIGO_JOGAR);
			
			DynaActionForm myForm = (DynaActionForm) form;
			
			log.setIdObjeto((Integer)myForm.get("jogo"));
			log.setFases((Integer)myForm.get("fases"));
			log.setTempo((Integer)myForm.get("tempo"));
			log.setPontuacao((Integer)myForm.get("pontuacao"));
			log.setMetaAlternativa((Integer)myForm.get("metaAlternativa"));
			
			facade.saveLog(log);
		}
		
		return mapping.findForward("fcloseWindow");
	}
	
	public ActionForward saveLogViewMaterial(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{
		DynaActionForm myForm = (DynaActionForm) form;
		
		//TODO - LOG - Visualizacao de Material - OK
		Log log = SystemActions.getLogUser(request);
		log.setCodigo(Log.LOG_CODIGO_VISUALIZACAO_MATERIAL);
		log.setIdObjeto((Integer)myForm.get("idMaterial"));
		facade.saveLog(log);
		
		System.out.println("LOG - saveLogViewMaterial - external link");
		
		return null;
	}
	
	public ActionForward saveLogViewVideo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{
		DynaActionForm myForm = (DynaActionForm) form;
		
		//TODO - LOG - Visualizacao de Video - OK
		Log log = SystemActions.getLogUser(request);
		log.setCodigo(Log.LOG_CODIGO_VISUALIZACAO_VIDEO);
		log.setIdObjeto((Integer)myForm.get("idMaterial"));
		facade.saveLog(log);
		
		return null;
	}
}
