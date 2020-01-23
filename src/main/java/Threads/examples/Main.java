package Threads.examples;

public class Main {

  public static void main(String[] args) {

    /*TODO Example 1:

    *  When the interrupt method is called on a thread, the interrupted status of the thread
     	 is set. This is a boolean flag that is present in every thread. Each thread should
     	 occasionally check whether it has been interrupted.

     	 *To find out whether the interrupted status was set, first call the static
     Thread.currentThread method to get the current thread, and then call the isInterrupted
     method:


     	  while (!Thread.currentThread().isInterrupted() && more work to do)
     	  {
     		  do more work
     	  }

     	  */

    /*TODO Example 2":

    * There is no language requirement that a thread which is interrupted should terminate. Interrupting a thread simply grabs its attention. The interrupted thread
    can decide how to react to the interruption. Some threads are so important that
    they should handle the exception and continue. But quite commonly, a thread
    will simply want to interpret an interruption as a request for termination. The run
    method of such a thread has the following form:
            Runnable r = () -> {
         try
         {
         . . .
         while (!Thread.currentThread().isInterrupted() && more work to do)
         {
        do more work
         }
         }
         catch(InterruptedException e)
         {
         // thread was interrupted during sleep or wait
         }
         finally
         {
        cleanup, if required
         }
         // ex

             */

    /*TODO Example 3:

        * The isInterrupted check is neither necessary nor useful if you call the sleep method
    (or another interruptible method) after every work iteration. If you call the sleep
    method when the interrupted status is set, it doesn’t sleep. Instead, it clears the
    status (!) and throws an InterruptedException. Therefore, if your loop calls sleep, don’t
    check the interrupted status. Instead, catch the InterruptedException,

            Runnable r = () -> {
         try
         {
         . . .
         while (more work to do)
         {
        do more work
         Thread.sleep(delay);
         }
         }
         catch(InterruptedException e)
         {
         // thread was interrupted during sleep
         }
         finally
         {
        cleanup, if required
         }
         // exiting the run method terminates the thread
        };


             */

    /*TODO Example 4: Lock Objects

           *There are two mechanisms for protecting a code block from concurrent access.
    The Java language provides a synchronized keyword for this purpose, and Java SE 5.0
    introduced the ReentrantLock class

    // The basic outline for protecting a code block with a ReentrantLock is:
           myLock.lock(); // a ReentrantLock object
        try
        {
        critical section
        }
        finally
        {
         myLock.unlock(); // make sure the lock is unlocked even if an exception is thrown
        }


             */

  }
}
