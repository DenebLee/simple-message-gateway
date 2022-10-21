package message;

import kr.nanoit.model.message.MessageType;
import kr.nanoit.old.exception.message.DeleteException;
import kr.nanoit.old.exception.message.SelectException;
import kr.nanoit.old.exception.message.UpdateException;
import kr.nanoit.old.exception.message.InsertException;
import kr.nanoit.model.message.ReceiveMessageDto;
import kr.nanoit.repository.ReceivedMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class ReceivedMessageRepositoryImplTest extends TestSetUp {
    private ReceivedMessageRepository receivedMessageRepository;

    public ReceivedMessageRepositoryImplTest() throws IOException {
        super("RECEIVE");
    }

    @BeforeEach
    void setUp() throws DeleteException, IOException {
        receivedMessageRepository = ReceivedMessageRepository.createMybatis(properties);
        receivedMessageRepository.deleteAll();
    }

    @Test
    @DisplayName("RECEIVE -> FIND BY ID")
    void should_receive_select_by_id() throws InsertException, SelectException {
        // given
        ReceiveMessageDto expected = new ReceiveMessageDto(0, MessageType.SMS, new Timestamp(System.currentTimeMillis()), 4, "010-4040-4141", "010-4444-5555", "테스트");
        receivedMessageRepository.save(expected);

        // when
        ReceiveMessageDto actual = receivedMessageRepository.findById(expected.getId());

        // then
        assertThat(actual.getMessage_type()).isEqualTo(expected.getMessage_type());
        assertThat(actual.getReceived_time()).isEqualTo(expected.getReceived_time());
        assertThat(actual.getSender_agent_id()).isEqualTo(expected.getSender_agent_id());
        assertThat(actual.getFrom_phone_number()).isEqualTo(expected.getFrom_phone_number());
        assertThat(actual.getTo_phone_number()).isEqualTo(expected.getTo_phone_number());
        assertThat(actual.getMessage_content()).isEqualTo(expected.getMessage_content());
    }

    @Test
    @DisplayName("RECEIVE -> COUNT")
    void should_receive_return_number_of_count() throws SelectException, InsertException {
        // given
        ReceiveMessageDto expected = new ReceiveMessageDto(0, MessageType.SMS, new Timestamp(System.currentTimeMillis()), 4, "010-4040-4141", "010-4444-5555", "테스트");
        receivedMessageRepository.save(expected);

        // when
        int actual = receivedMessageRepository.count();

        // then
        assertThat(actual).isEqualTo(1);
    }


    @Test
    @DisplayName("RECEIVE -> DELETE BY ID")
    void should_receive_delete_by_id() throws InsertException, DeleteException {
        // given
        ReceiveMessageDto expected = new ReceiveMessageDto(0, MessageType.SMS, new Timestamp(System.currentTimeMillis()), 4, "010-4040-4141", "010-4444-5555", "테스트");
        receivedMessageRepository.save(expected);

        // when
        boolean actual = receivedMessageRepository.delete(expected.getId());

        // then
        assertThat(actual).isTrue();
//        assertThat(receivedMessageRepository.findById(expected.getId())).isNull();

    }

    @Test
    @DisplayName("RECEIVE -> DELETE ALL")
    void should_receive_delete_all() throws DeleteException, InsertException {
        // given -> 100 칼럼 생성
        List<ReceiveMessageDto> expected = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            expected.add(new ReceiveMessageDto(0, MessageType.SMS, new Timestamp(System.currentTimeMillis()), i, "010-4040-4141" + i, "010-4444-5555" + i, "테스트" + i));
        }
        receivedMessageRepository.saveAll(expected);
        // when
        boolean actual = receivedMessageRepository.deleteAll();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("RECEIVE -> DELETE ALL BY CONDITION")
    void should_receive_delete_all_when_request_condition() {

    }

    @Test
    @DisplayName("RECEIVE -> EXISTS BY ID")
    void should_exists_by_id() throws InsertException, SelectException {
        // given
        ReceiveMessageDto expected = new ReceiveMessageDto(0, MessageType.SMS, new Timestamp(System.currentTimeMillis()), 4, "010-4040-4141", "010-4444-5555", "테스트");
        receivedMessageRepository.save(expected);
        long id = expected.getId();

        // when
        boolean actual = receivedMessageRepository.existsById(id);

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("RECEIVE -> UPDATE")
    void should_receive_update() throws InsertException, UpdateException, SelectException {
        // given
        ReceiveMessageDto originalData = new ReceiveMessageDto(0, MessageType.SMS, new Timestamp(System.currentTimeMillis()), 4, "010-4040-4141", "010-4444-5555", "테스트");
        receivedMessageRepository.save(originalData);
        long id = originalData.getId();
        ReceiveMessageDto expected = new ReceiveMessageDto(id, MessageType.NONE, new Timestamp(System.currentTimeMillis()), 2, "010101010", "010-4444-5555", "안녕하세요 저는 이정섭입니다");

        // when
        int actual = receivedMessageRepository.update(expected);

        // then
        assertThat(actual).isEqualTo(1);
        assertThat(receivedMessageRepository.findById(expected.getId())).isNotEqualTo(originalData);
    }


    @Test
    @DisplayName("RECEIVE -> FIND BY ID")
    void should_receive_return_result_when_select_message_id() throws InsertException, SelectException {
        // given
        ReceiveMessageDto expected = new ReceiveMessageDto(0, MessageType.SMS, new Timestamp(System.currentTimeMillis()), 4, "010-4040-4141", "010-4444-5555", "테스트");
        receivedMessageRepository.save(expected);
        long id = expected.getId();

        // when
        ReceiveMessageDto actual = receivedMessageRepository.findById(id);

        // then
        assertThat(actual.getMessage_type()).isEqualTo(expected.getMessage_type());
        assertThat(actual.getReceived_time()).isEqualTo(expected.getReceived_time());
        assertThat(actual.getTo_phone_number()).isEqualTo(expected.getTo_phone_number());
        assertThat(actual.getFrom_phone_number()).isEqualTo(expected.getFrom_phone_number());
        assertThat(actual.getSender_agent_id()).isEqualTo(expected.getSender_agent_id());
        assertThat(actual.getMessage_content()).isEqualTo(expected.getMessage_content());
    }

    @Test
    @DisplayName("RECEIVE -> INSERT")
    void should_receive_insert() throws InsertException {

        // given
        ReceiveMessageDto expected = new ReceiveMessageDto(0, MessageType.SMS, new Timestamp(System.currentTimeMillis()), 4, "010-4040-4141", "010-4444-5555", "테스트");

        // when
        int acutal = receivedMessageRepository.save(expected);

        // then
        assertThat(acutal).isEqualTo(1);
    }

    @Test
    @DisplayName("RECEIVE -> SAVE ALL")
    void should_receive_save_entire_list() throws InsertException {
        // given
        List<ReceiveMessageDto> expected = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            expected.add(new ReceiveMessageDto(0, MessageType.SMS, new Timestamp(System.currentTimeMillis()), i, "010-4040-4141" + i, "010-4444-5555" + i, "테스트" + i));
        }
        System.out.println(expected);
        // when
        int actual = receivedMessageRepository.saveAll(expected);

        // then
        assertThat(actual).isEqualTo(expected.size());
    }

}



