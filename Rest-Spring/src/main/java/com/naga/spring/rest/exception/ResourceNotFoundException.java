/**
 * 
 */
package com.naga.spring.rest.exception;

/**
 * @author Vedasri
 *
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Long resourceId;
	
	public ResourceNotFoundException(Long resourceId, String message) {
		super(message);
		this.resourceId = resourceId;
	}
}
