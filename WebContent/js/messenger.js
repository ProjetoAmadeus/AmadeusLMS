/**
 * By Nailson Cunha
 * Scripts do sistema de mensagens inbox do Sistema Amadeus
 */

$(document).ready(function(){	

	/*
	 * função de timer para verificacao de status do usuario
	 * Baseia-se no ID do elemento LI
	 */	

	function checkStatus(){
		$('ul.all-participants li').each(function(index,element){
			if( $(this).hasClass('participant-name') )
				$.get('user.do?method=userStatus&accessInfoId='+ element.id, function(data){
					if(data == 1){
						$(element).removeClass('offlineUser').addClass('onlineUser');
					} else {
						$(element).removeClass('onlineUser').addClass('offlineUser');
					}
				});
		});
	}
	
	checkStatus(); //Checa o status assim que carrega a p�gina
	setInterval(function(){checkStatus();}, 15000); //Fica checando o status a cada 15 segundos
	
	/* Fim do timer */
	
	//Funcao que recebe o objeto json, transforma em mensagem e exibe na div das mensagens
	function addMessageToList(message){		
		var divToInsert = '<div class="the-message">'
					+	'<span class="from-user"><small><b>' + message.sender + '</b></small></span><br />'
					+	'<span class="message-date"><small>'+ $.datepicker.formatDate('dd-mm-yy',new Date(message.date)) +'</small>'
					+	'</span>'
					+	'<h3 class="the-message-title"><a href="#" id="' + message.id + '" class="link-message-title">' + message.title + '</a></h3>'
					+	'<p class="the-message-content" id="message-content-' + message.id + '">' + message.content + '<br /><br />'
					+	'<a class="message-reply-link" title="Re: ' + message.title + '" href="messenger.do?method=replyMessage&messageId='+ message.id 
					+	'&senderId=' + message.senderId + '">Responder</a>'
					+	'<a class="message-delete-link" href="messenger.do?method=deleteMessage&messageId=' + message.id + '">Excluir</a>'
					+	((message.toAll) ? 
							'<a class="reply-to-all-link" title="Re: ' + message.title + '" href="messenger.do?method=replyMessageToAll&messageId=' + message.id + '&senderId=' + message.senderId + '&courseId=' + message.courseId + '">Responder a todos</a>'
							: '')
					+	'</p>'
					+	'<hr />'
					+	'</div>';
		
		$(divToInsert).insertBefore($('div.the-message').first());
	}
	
	//Verificaçao de mensagens do usuário logado.
	function checkMessages(){
		var idsToIgnore = new Array();
		var courseId = $('#hidden-course-id').val();
		$('a.link-message-title').each(function(){
			idsToIgnore.push($(this).attr('id'));
		});
		
		var ignore = $.param({'ignoreIds':idsToIgnore});
		
		$.ajax({
			type: 'GET',
			url: 'messenger.do?method=getUnreadMessages&courseId='+ courseId + '&' +ignore,
			dataType: 'json',
			data: {},
			success: function(data){
				if( (data != null) && (data.length) )
					$.each(data, function(key, item){
						addMessageToList(item);
					});
			}
		});
	};
	
	//Programa o timer do check mensagens
	if($('div#seemessages').length){
		setInterval(function(){
			checkMessages();
		}, 30000);		
	}		
	/* Fim do check mensagens */
	
	
	//funcao para limpar o campo de texto mensagem
	function limparCampos(){
		$('.ipt-assunto').val('');
		$('.txtarea-mensagem').val('');
		$('textarea#content-to-all').val('');
	}
	
	//mensagem de sucesso no envio das mensagens
	function exibeMensagemSucesso(){
		var divSucesso = $('<div>',{
							id: 'divsucesso',
							'class': 'divsucesso',
							html: 'Mensagem enviada!'
						});
		divSucesso.appendTo('body');
		setTimeout(function(){
			$('#divsucesso').remove();
		},3000);
	}
	
	//mensagem de erro, nao pode ser vazio
	function exibeMensagemNaoVazio(campo){
		var divNaovazio = $('<div>',{
							id: 'naovazio',
							'class': 'naovazio',
							html: 'Campo ' + campo + ' está vazio!'
						});
		divNaovazio.appendTo('body');
		setTimeout(function(){
			$('#naovazio').remove();
		},3000);
	}
	
	
	// Exibe-oculta o form de envio de mensagens para All Participants
	$('#all-participants').click(function(){
		$('.send-message').hide();
		$('.send-message-0').toggle();
	});
	
	// Exibe-oculta o form de envio de mensagens para o usu�rio clicado.
	$('.participant-name').click(function(){
		var elemento = $('#sendto-' + $(this).attr('id')); //Captura o elemento div com base no id do Elemento <li> clicado.
		$('.send-message-0').hide();					   // Oculta o div all-participants
		$('.send-message[id!=' + elemento.attr('id') + ']').hide(); //Oculta todos os divs send-message exceto o que tiver a div referente ao <li> clicado
		elemento.toggle();
	});
	
	
	//Oculta o devido form de envio de mensagem ap�s o clique no botao cancelar.
	$('.btn-cancelar').click(function(e){
		$(e.target).closest('.send-message-0').hide();
		$(e.target).closest('.send-message').hide();
		limparCampos();
	});
	
	//Envio da mensagem via form
	$('.send-msg').submit(function(event){
		event.preventDefault();
		var tmpText = $(this).children('.txtarea-mensagem').val();
		var tempTitle = $(this).children('.ipt-assunto').val();
		var courseId = $('#hidden-course-id').val();
		
		if( tmpText == '' || tmpText.replace(/\s+/, '') == '' ){ //verifica se a mensagem está vazia.
			exibeMensagemNaoVazio('mensagem');
			return false;
		}
		if( tempTitle == '' || tempTitle.replace(/\s+/, '') == '' || tempTitle == 'Assunto' ){ //verifica se a mensagem está vazia.
			exibeMensagemNaoVazio('assunto');
			return false;
		}
			
		var metodo = 'method=saveMessage';
		var valores = $(this).serialize();
		var alldata = metodo + '&' + valores + '&' + courseId;
		$.ajax({
			type: 'POST',
			url: 'messenger.do',
			data: alldata,
			success: exibeMensagemSucesso()
		});
		limparCampos();
		return false;
	});
	
	//Estilo do input assunto
	$('.ipt-assunto').val('Assunto');
	$('.ipt-assunto').focus(function(){
		if($(this).val()=='Assunto'){			
			$(this).css('color','black');
			$(this).val('');
		}
	});
	$('.ipt-assunto').focusout(function(){
		if($(this).val()=='' || $(this).val().replace(/\s+/, '') == '' ){			
			$(this).css('color','#ccc');
			$(this).val('Assunto');
		}
	});
	
	
	/*
	 * 
	 * Funções referentes as exibições das mensagens
	 * 
	 */
	
	//função que envia, via ajax, a resposta de uma determinada mensagem
	//recebe do modal form um array com dois parametros como valores, o 0 é a url e o 1 são os paramentro para o request
	function sendReply(parametros){
		var replyTitle = $('#reply-assunto').val();
		var replyContent = $('#reply-content').val();
		var params = {
				messageTitle: replyTitle,
				messageContent: replyContent
		};
		
		
		if( replyContent == '' || replyContent.replace(/\s+/, '') == '' ){ //verifica se a mensagem está vazia.
			exibeMensagemNaoVazio('mensagem');
			return false;
		}

		$.ajax({
			type: 'POST',
			url: parametros[0],
			data: parametros[1] + '&' + $.param(params),
			success: exibeMensagemSucesso()
		});
		
	}
	
	//Função que torna marca uma mensagem como lida, no banco
	function setMessageAsRead(pid){
		var metodo = 'setMessageAsRead';
		$.ajax({
			type: 'POST',
			url: 'messenger.do',
			data: {method: metodo, messageId: pid},
			success: function(){
				setTimeout(function(){
					if(!$('a#'+pid).hasClass('lida'))
						$('a#'+pid).addClass('lida');
				}, 3000);
			}
		});
	}
	
	
	//Exibe ou oculta a mensagem ao se clicar no título. referente a barra lateral direita.
	$('a.link-message-title').live('click',function(event){
		event.preventDefault();
		var pid = $(this).attr('id');
		
		$('p#message-content-'+pid).toggle('fast');
		
		setMessageAsRead(pid);		
	});
	
	//Funcao que exibe o dialog de resposta
	function showReplyDialog(title,href){
		$('#form-reply-to').load('jsp/messenger/form-reply.jsp', function(){
			if($('#reply-assunto').length){
				$('#reply-assunto').val(title);
				$('#reply-content').focus();
			}
		});
		
		$('#form-reply-to').dialog({
			autoOpen: true,
			height: 250,
			width: 350,
			modal: true,
			resizable: false,
			buttons: {
				'Enviar': function(){
					sendReply(href);
					$( this ).dialog( 'close' );
					$('#form-reply-to').remove('form');
				},
				'Cancelar': function(){
					$( this ).dialog( 'close' );
					$('#form-reply-to').remove('form');					
				}
			} ,
			close: function(){
				$('#form-reply-to').remove('form');
			}
		});
	}
	
	//Exibição do Modal Form de resposta
	$('.message-reply-link').live('click',function(event){
		event.preventDefault();
		var title = $(this).attr('title');
		var href = $(this).attr('href').split('?');
		
		showReplyDialog(title,href);		
	});
	
	/*
	 * Excluir mensagens
	 */
	
	//funcao que exclui a mensagem e a remove da tela
	function excluirMensagem(href,element){
		
		$.ajax({
			type: 'POST',
			url: href[0],
			data: href[1],
			success: function(){
				element.parent('.the-message-content').parent('.the-message').remove();				
			}
		});
		
	}
	
	$('.message-delete-link').live('click',function(e){
		e.preventDefault();
		
		var element = $(this);
		var $confirmation = $('<div id="confirmation" title="Excluir">Confirma exclusão da mensagem?</div>');
		var href = $(this).attr('href').split('?');

		$confirmation.dialog({
            resizable: false,
            height:140,
            modal: true,
            buttons: {
                Excluir: function() {
                    excluirMensagem(href,element);
                    $( this ).dialog( 'close' );
                },
                Cancelar: function() {
                    $( this ).dialog( 'close' );
                }
            }
        });
		
	});
	
	/*
	 * Funções de enviar para todos
	 */
	
	$('form#form-send-to-all').submit(function(e){
		e.preventDefault();
		
		var action = $(this).attr('action');
		var metodo = 'method=sendMessageToAll';
		
		var assuntoToAll = $('#assunto-to-all').val();
		if( assuntoToAll == '' || assuntoToAll.replace(/\s+/, '') == '' || assuntoToAll == 'Assunto' ){ //verifica se a mensagem está vazia.
			exibeMensagemNaoVazio('assunto');
			return false;
		}
		
		var contentToAll = $('textarea#content-to-all').val();
		if( contentToAll == '' || contentToAll.replace(/\s+/, '') == '' ){ //verifica se a mensagem está vazia.
			exibeMensagemNaoVazio('mensagem');
			return false;
		}
		
		var allData = metodo + '&' + $(this).serialize();
		
		$.ajax({
			type: 'POST',
			url: action,
			data: allData,
			success: exibeMensagemSucesso()
		});
		limparCampos();
		return false;
	});
	
	//Funcao de responder a todos
	$('.reply-to-all-link').live('click', function(e){
		e.preventDefault();
		var title = $(this).attr('title');
		var href = $(this).attr('href').split('?');
		
		showReplyDialog(title,href);
	});
	
});