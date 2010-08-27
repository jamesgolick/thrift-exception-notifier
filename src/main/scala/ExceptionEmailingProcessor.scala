package exceptionnotifier

import com.codahale.logula.Logging

import net.liftweb.util.Mailer
import net.liftweb.util.Mailer._

import org.apache.thrift.TProcessor
import org.apache.thrift.protocol.TProtocol
import org.apache.thrift.transport.TTransportException;

class ExceptionEmailingProcessor(processor: TProcessor, 
                                 from:      String,
                                 to:        String,
                                 subject:   String,
                                 host:      String) 
  extends TProcessor with Logging {

  System.setProperty("mail.smtp.host", host)

  def process(in: TProtocol, out: TProtocol): Boolean = {
    try {
      processor.process(in, out)
    } catch {
      case t: TTransportException => throw t
      case e: Throwable => 
        try {
          send(e)
        } catch {
          case t: Throwable => log.severe(t, "Error sending mail.")
        }
        throw e
    }
  }

  private def send(e: Throwable) = {
    val body = "%s\n%s\n%s".format(e.getClass.getName, e.getMessage, e.getStackTrace.map("\tat %s".format(_)).mkString("\n"))
    sendMail(From(from),
             Subject(subject),
             To(to),
             PlainMailBodyType(body))
  }
}
