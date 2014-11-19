/**
 * Author Nailson Cunha
 */

$(document).ready(function(){
	
	/*
	 * Exibir gif de load. 
	 */
	
	function showLoading(){
		$loading = '<img src="themes/default/img/ajax-loader2.gif" id="img-loading" />';
		$('#waiting').append($loading);
	}
	
	function removeLoading(){
		$('#img-loading').remove();
	}
	
	// Chamada do monitorar redes sociais
	$('#monitoring-start').click(function(event){
		var hashtag = $('#ipt-hashtag').val();
		
		if( hashtag == '' || hashtag.replace(/\s+/, '') == ''){
			alert('Campo Hashtag Vazio!');
			$('#ipt-hashtag').val('');
			return;
		}
		
		$.ajax({
			url: 'social.do?method=startSocialNetworkMonitoring&hashtag=' + hashtag
		});
		
		showLoading();
		
		$(this).attr('disabled','disabled');
		$('#ipt-hashtag').attr('disabled','disabled');
		$(this).addClass('monitoring-start-disabled');
		
	});

	// Parar monitoramento das redes sociais
	$('#monitoring-stop').click(function(event){
		
		$.ajax({
			url: 'social.do?method=stopSocialNetworkMonitoring'
		});
		
		removeLoading();
		
		$('#monitoring-start').removeAttr('disabled');
		$('#ipt-hashtag').removeAttr('disabled');
		$('#monitoring-start').removeClass('monitoring-start-disabled');
				
	});
	
	// Alerta do input Hashtag
	$('#ipt-hashtag').focus(function(event){
		$('#hash-alert').html('Não utilize o #');
	});
	$('#ipt-hashtag').focusout(function(event){
		$('#hash-alert').html('');
	});
	
	
	// Controle do monitoramento do twitter
	var urlToCheck = 'http://localhost:8080/amadeuslms/social.do?method=showViewSocialNetworkMonitoring&courseId=18';
	if(window.location == urlToCheck){
		$(window).bind('beforeunload', function(){
			if($('#monitoring-start').attr('disabled') == 'disabled'){
				return 'Existe um monitoramento em execução, pare-o antes de sair.';
				$.ajax({
					url: 'social.do?method=stopSocialNetworkMonitoring'
				});
			}
		});
	}
	
});