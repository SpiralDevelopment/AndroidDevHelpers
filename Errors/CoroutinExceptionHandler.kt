    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()

            when (throwable) {
                is ConnectException -> {
                    // No internet error - Offline might occur here    
                }
                is UnknownHostException -> {
                    // No internet error - Offline might occur here                  
                }
                is SocketTimeoutException -> {
                    // No internet error - Offline might occur here    
                }
                else -> {
                    
                }
            }

        }
