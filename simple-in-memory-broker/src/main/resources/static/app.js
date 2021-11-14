class WebSocketController {

  constructor() {
    this._onConnected = this._onConnected.bind(this);
  }

  connect() {
    // Java: r.addEndpoint("/stomp-websocket-endpoint")
    const socket = new SockJS('/stomp-websocket-endpoint');
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect({}, this._onConnected);
    const inputElement = this._setupKeyPressListener();
    inputElement.autofocus = true;
  }

  _onConnected(frame) {
    this._setConnected(true); // console.log('Connected: ' + frame);
    // Java: r.enableSimpleBroker("/topic")
    // Java: @SendTo("/topic/messages")
    this.stompClient.subscribe('/topic/messages', this._showMessage);
    // Java: @SendTo("/topic/errors")
    this.stompClient.subscribe('/topic/errors', this._showError);
  }

  _setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('message').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('messages-container').innerHTML = '';
  }

  _setupKeyPressListener() {
    const that = this;
    const inputElement = document.getElementById('text');
    inputElement.addEventListener('keydown', function (event) {
      if (event.key === 'Enter') that.sendMessage();
    }, false);
    return inputElement;
  }

  disconnect() {
    if (this.stompClient != null) this.stompClient.disconnect();
    this._setConnected(false); // console.log('Disconnected');
    this._removeKeyPressListener();
  }

  _removeKeyPressListener() {
    document.getElementById('text').removeEventListener('keydown', null, false);
  }

  sendMessage() {
    const htmlElement = document.getElementById('text');
    const sendMessageCommand = { message: htmlElement.value };
    const sendMessageCommandJsonBody = JSON.stringify(sendMessageCommand);
    // Java: r.setApplicationDestinationPrefixes("/stomp-application")
    // Java: @MessageMapping("/messages")
    this.stompClient.send('/stomp-application/messages', {}, sendMessageCommandJsonBody);
    htmlElement.value = '';
  }

  _showMessage(message) {
    const messageDocumentJsonBody = message.body;
    const messageDocument = JSON.parse(messageDocumentJsonBody);
    const { message: messageContent, createdAt } = messageDocument;
    const messagesContainer = document.getElementById('messages-container');
    const messageDiv = document.createElement('div');
    messageDiv.style.wordWrap = 'break-word';
    messageDiv.appendChild(document.createTextNode(createdAt + ': ' + messageContent));
    messagesContainer.prepend(messageDiv);
  }

  _showError(errorMessage) {
    const errorMessageDocumentJsonBody = errorMessage.body;
    const errorMessageDocument = JSON.parse(errorMessageDocumentJsonBody);
    const { error: errorMessageContent } = errorMessageDocument;
    console.error(errorMessageContent);
  }
}

const webSocket = new WebSocketController();
