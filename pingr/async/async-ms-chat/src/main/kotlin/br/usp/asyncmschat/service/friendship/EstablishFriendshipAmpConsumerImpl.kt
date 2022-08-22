package br.usp.asyncmschat.service.friendship

import br.usp.asyncmschat.model.entity.Friendship
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class EstablishFriendshipAmpConsumerImpl(
    private val friendshipService: FriendshipService
): EstablishFriendshipAmpConsumer {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @RabbitListener(queues = ["new-friendship"])
    override fun consume(friendship: Friendship) {
        log.info("consume() : received new friendship : {}", friendship)

        if (!friendshipService.exists(friendship)) {
            friendshipService.save(friendship)
        }
    }
}
