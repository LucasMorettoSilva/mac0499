package br.usp.asyncmschat.model.response

import br.usp.asyncmschat.model.entity.Chat
import br.usp.asyncmschat.model.entity.Message

class FullChat(

    var chat: Chat,

    var users: List<String>,

    var messages: List<Message>
)
