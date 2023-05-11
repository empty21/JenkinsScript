def send(def payload) {
    try {
        if (env.TG_BOT_TOKEN == null) {
            throw new Exception("TG_BOT_TOKEN is not set")
        }
        def chatId = payload.chatId != null ? payload.chatId : env.TG_NOTIFICATION_CHAT
        if (chatId == null) {
            throw new Exception("CHAT_ID is not set")
        }
        if (payload.message == null) {
            throw new Exception("Message is not set")
        }
        String telegramApi = "https://api.telegram.org/bot${env.TG_BOT_TOKEN}/sendMessage"
        String shCommand = "curl -X POST $telegramApi -d chat_id=$chatId -d text=\"${payload.message}\" -d parse_mode='markdown'"
        sh(shCommand)
    } catch (Exception e) {
        println("Could not send telegram message: ${e.getMessage()}")
    }
}
