package io.github.devtamakuwala.whatsapp_clone.whatsappclone.message;

import io.github.devtamakuwala.whatsapp_clone.whatsappclone.chat.Chat;
import io.github.devtamakuwala.whatsapp_clone.whatsappclone.common.BaseAuditingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "messages")
@NamedQuery(name = MessageConstant.FIND_MESSAGES_BY_CHAT_ID, query = "SELECT m FROM Message m WHERE m.chat.id = :chatId ORDERED BY m.createdDate")
@NamedQuery(name = MessageConstant.SET_MESSAGES_TO_SEEN_BY_CHAT, query = "UPDATE Message SET state = :newState WHERE chat.id = :chatId")
public class Message extends BaseAuditingEntity {

    @Id
    @SequenceGenerator(name = "msg_seq", sequenceName = "msg_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "msg_seq")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;
    @Enumerated(EnumType.STRING)
    private MessageState state;
    @Enumerated(EnumType.STRING)
    private MessageType type;

    @ManyToOne()
    @JoinColumn(name = "chat_id")
    private Chat chat;
    @Column(nullable = false, name = "sender_id")
    private String senderId;
    @Column(nullable = false, name = "receiver_id")
    private String receiverId;

}
