Thrift Exception Notifier
=========================

If you run thrift services in production, it's handy to receive an email when shit breaks.

## How to use it

Essentially, all this does is decorate TProcessor. So, just instantiate it with your TProcessor instance and some mail config:

  val processor         = new MyService.Processor(handler)
  val emailingProcessor = new ExceptionEmailingProcessor(processor = processor,
                                                         from      = "myservice@%s".format(hostname),
                                                         to        = "shitisbroken@mysite.com",
                                                         subject   = "[MyService] Your codez are broken, bro.",
                                                         host      = "smtp.mysite.com")

## What it doesn't do

SMTP authentication or TLS or any bullshit like that. Easy to add though. See the docs for liftweb/javax.mail for how.

## What sucks about it

It depends on liftweb-util. It's a bunch of crap, but it works, so whatever.

## Credits

Copyright 2010 James Golick, Protose Inc.
