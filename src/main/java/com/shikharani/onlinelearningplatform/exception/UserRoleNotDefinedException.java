package com.shikharani.onlinelearningplatform.exception;

/**
 * Customer exception will be thrown when user role is unknown.
 */
public class UserRoleNotDefinedException extends RuntimeException {

	public UserRoleNotDefinedException() {
		super("User role is unknown.");
	}

}
