package com.choschi.memdroid.util;

import com.choschi.memdroid.Client.ClientMessages;

//TODO move this to another more meaningful package

public interface ClientListener {
	void notify (ClientMessages message);
}
