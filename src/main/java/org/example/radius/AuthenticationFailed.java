package org.example.radius;

class AuthenticationFailed extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AuthenticationFailed(Throwable cause) {
		super(cause);
	}
}
