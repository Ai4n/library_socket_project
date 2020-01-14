package com.ai4n.socketExchange.model.socketExchange;

import com.ai4n.socketExchange.model.ServerMessage;
import com.ai4n.socketExchange.model.SocketExchange;

public class QuitAndCloseSessionRequest extends SocketExchange {

    public QuitAndCloseSessionRequest() {
        super(ServerMessage.CLOSE_SESSION);
    }
}
