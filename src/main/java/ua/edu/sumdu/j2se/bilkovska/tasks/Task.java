package ua.edu.sumdu.j2se.bilkovska.tasks;

/**
 * Class Task is needed to create 'task' objects.
 * Those can be active and inactive, one-time and repeated.
 */
public class Task {

    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;

    /**
     * Constructor for one-time tasks
     * @param title    name of task
     * @param time     time when one-time task is planned for execution
     */
    public Task(String title, int time) {
        this.title = title;
        this.time = time;
    }

    /**
     * Constructor for repeated tasks
     * @param title     name of task
     * @param start     time when repeated task execution is to be started
     * @param end       time when repeated task execution is planned to be finished
     * @param interval  interval between each iteration of repeated task execution
     */
    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    /**
     * Getter for task name
     * @return    name of task
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for task name
     * @param title    name of task
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for task status (if it is active or not)
     * @return    task status
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Setter for task status (if it is active or not)
     * @param active   task status
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Getter for time when one-time task is planned for execution.
     * @return    time when one-time task is planned for execution
     * If it is repeated task then method returns time when repeated task execution is to be started
     */
    public int getTime() {
        int getTimeResult = 0;
        if (interval == 0) {
            getTimeResult = time;
        } else if ((end - start) / interval >= 1) {
            getTimeResult = start;
        }
        return getTimeResult;
    }

    /**
     * Setter for time when one-time task is planned for execution.
     *
     * If task is repeated one, then it turns to one-time.
     * Its excessive parameters (start, end, interval) are set as 0 (default int value).
     *
     * @param time     time when one-time task is planned for execution
     */
    public void setTime(int time) {
        this.time = time;
        start = 0;
        end = 0;
        interval = 0;
    }

    /**
     * Setter for time when repeated task execution is to be started.
     *
     * If task is one-time, then it turns to repeated one.
     * Its initial time parameter is changed to 0 (default int value).
     *
     * @param start     time when repeated task execution is to be started
     * @param end       time when repeated task execution is planned to be finished
     * @param interval  interval between each iteration of repeated task execution
     */
    public void setTime(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
        time = 0;
    }

    /**
     * Getter for time when repeated task execution is to be started.
     * If task is one-time than its 'time' parameter is returned.
     * @return     time when repeated task execution is to be started
     */
    public int getStartTime() {
        int getStartTimeResult = 0;
        if (interval == 0) {
            getStartTimeResult = time;
        } else if ((end - start) / interval >= 1) {
            getStartTimeResult = start;
        }
        return getStartTimeResult;
    }

    /**
     * Getter for time when repeated task execution is planned to be finished.
     * If it is one-time task than its time parameter is returned.
     * @return    time when repeated task execution is planned to be finished
     */
    public int getEndTime() {
        int getEndTimeResult = 0;
        if (interval == 0) {
            getEndTimeResult = time;
        } else if ((end - start) / interval >= 1) {
            getEndTimeResult = end;
        }
        return getEndTimeResult;
    }

    /**
     * Getter for interval between each iteration of repeated task execution
     * If it is one-time task that 0 is returned.
     * @return    interval between each iteration of repeated task execution
     */
    public int getRepeatInterval() {
        int getRepeatedIntervalResult = 0;
        if ((interval != 0) && ((end - start) / interval >= 1)) {
            getRepeatedIntervalResult = interval;
        }
        return getRepeatedIntervalResult;
    }

    /**
     * Getter for tasks to distinguish if it is one-time or repeated task.
     * @return    true for repated task
     */
    public boolean isRepeated() {
        return interval != 0;
    }

    /**
     * Getter for start time of next iteration for active repeated tasks
     * and for time when active one-time task is planned for execution if this moment is not in the past.
     *
     * If at current moment task is not to be repeated or if task is inactive, -1 is to be returned.
     * @param current    current moment of time
     * @return           start time of next iteration for active repeated tasks
     */
    public int nextTimeAfter(int current) {
        int nextTimeAfterResult = 0;
        if (!active) {
            nextTimeAfterResult = -1;
        } else if ((interval == 0) && current < time) {
            nextTimeAfterResult = time;
        } else if (interval != 0 && (current < start)) {
            nextTimeAfterResult = start;
        } else if (interval != 0 && current + interval > end) {
            nextTimeAfterResult = -1;
        } else if ((interval != 0) && (current < end)) {
            for (int i = 1; (nextTimeAfterResult) <= current; i++) {
                nextTimeAfterResult = start + interval * i;
            }
        } else {
            nextTimeAfterResult = -1;
        }
        return nextTimeAfterResult;
    }


}
