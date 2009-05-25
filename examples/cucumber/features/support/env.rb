require 'rubygems'
require 'spec/expectations'
 
# START HACK
# Webrat's Selenium wrongly assumes Rails in available. 
# We'll just fool it...
module ActionController
  class IntegrationTest
  end
end

def silence_stream(*args)
  yield if block_given?
end
# END HACK

# Webrat
require 'webrat'
Webrat.configure do |config|
  #Alternatively, you may use :selenium
  config.mode = :mechanize
end

# normally: 
#require 'thistle'
# for dev: 
require '../../lib/thistle.rb'
require '../../lib/thistle_class.rb'
#include Thistle

World do
  session = Webrat::MechanizeSession.new
  session.extend(Webrat::Matchers)
  
  @thistle = Thistle::Thistle.new(session)
  #thistle.webrat_session = session
  #thistle
  #session
  #Thistle::webrat_session = session
  
  #@@webrat_session= session
 
end

World(Thistle)
 
# START HACK
# Disable Rails-specific code
module Webrat
  def self.start_app_server
  end

  def self.stop_app_server
  end
end
# END HACK
