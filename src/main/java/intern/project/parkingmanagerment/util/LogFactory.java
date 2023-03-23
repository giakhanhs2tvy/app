/**
* @author Vuong Nguyen
* @date   Mar 18, 2023
* @time   5:54:29 PM
* @version 1.0
*/
package intern.project.parkingmanagerment.util;

import org.apache.log4j.Logger;

/**
 * @author Vuong Nguyen
 *
 */
public class LogFactory {
	
	public static Logger getLogger(){
		Logger logger = Logger.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
		return logger;
	}

}
