package br.usp.syncmsconnection.model.response

import br.usp.syncmsconnection.model.entity.Chat
import br.usp.syncmsconnection.model.entity.Message

class FullChat(

    var chat: Chat,

    var users: List<String>,

    var messages: List<Message>
)
