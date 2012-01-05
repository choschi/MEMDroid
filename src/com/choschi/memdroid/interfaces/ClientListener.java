package com.choschi.memdroid.interfaces;

import com.choschi.memdroid.Client.ClientMessages;

public interface ClientListener {
	void notify (ClientMessages message);
}
