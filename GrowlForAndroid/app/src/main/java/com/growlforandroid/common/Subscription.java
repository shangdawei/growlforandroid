package com.growlforandroid.common;

import java.net.*;

import javax.jmdns.ServiceInfo;

public class Subscription {
	private final int _id;
	private final String _name;
	private final String _status;
	private final String _address;
	private final String _password;
	private final boolean _zeroConf;
	private final boolean _subscribed;
	private InetAddress[] _ipAddresses;

	public Subscription(int id, String name, String status, String address, String password, boolean zeroConf,
			boolean subscribed) {
		this(id, name, status, address, null, password, zeroConf, subscribed);
	}

	public Subscription(int id, String name, String status, String address, InetAddress[] ipAddresses, String password,
			boolean zeroConf, boolean subscribed) {
		_id = id;
		_name = name;
		_status = status;
		_address = address;
		_ipAddresses = ipAddresses;
		_password = password;
		_zeroConf = zeroConf;
		_subscribed = subscribed;
	}

	public Subscription(ServiceInfo service, String status) {
		this(0, service.getName(), status, service.getServer(), service.getInetAddresses(), "", true, false);
	}

	public int getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	public String getStatus() {
		return _status;
	}

	public String getAddress() {
		return _address;
	}

	public InetAddress[] getInetAddresses() {
		if (_ipAddresses == null) {
			try {
				_ipAddresses = InetAddress.getAllByName(_address);
			} catch (UnknownHostException e) {
				_ipAddresses = new InetAddress[0];
			}
		}
		return _ipAddresses;
	}

	public String getPassword() {
		return _password;
	}

	public boolean isZeroConf() {
		return _zeroConf;
	}

	public boolean isSubscribed() {
		return _subscribed;
	}

	/***
	 * True, if the subscription can be resolved to an IP address
	 * 
	 * @return
	 */
	public boolean isValid() {
		boolean hasIpAddress = (_ipAddresses != null) && (_ipAddresses.length > 0);
		return hasIpAddress;
	}

	public boolean matchesAny(InetAddress[] addresses) {
		for (InetAddress ipAddress : getInetAddresses()) {
			for (InetAddress address : addresses) {
				if (address.equals(ipAddress)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean matchesService(ServiceInfo service) {
		return _zeroConf && (_name == service.getServer());
	}
}
