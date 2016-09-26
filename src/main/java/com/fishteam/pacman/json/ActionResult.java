package com.fishteam.pacman.json;
/**
 * ActionResult contains result
 * of a MoveAction;
 * message that is shown
 * if you're blocked
 */
public class ActionResult {
	private boolean success;
	private String message;
	public ActionResult() {}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
