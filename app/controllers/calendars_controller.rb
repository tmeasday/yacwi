require 'rubygems'
require 'icalendar'
require 'date'

class CalendarsController < ApplicationController
  before_filter :authenticate, :except => :index
  
  def publish
    
    user = User.find_or_create(@username)
    
    cals = Icalendar.parse(request.body)
    cals.each { |cal|
      cal_uid = cal.properties['x-wr_relcalid']
      cal_name = cal.properties['x-wr_calname']
      
      c = user.calendars.find_by_uid(cal_uid)
      c.destroy unless c.nil?
      
      c = user.calendars.create({:name => cal_name, :uid => cal_uid})
      
      cal.events.each { |evt|         
        e = c.events.create( { :summary => evt.summary, 
                              :location => evt.location,
                              :notes => evt.description,
                              :start => evt.dtstart,
                              :end => evt.dtend } )
      } #end of events block
    } #end of calendar block

    render :text => "all is OK"
  end


  # GET /calendars
  # GET /calendars.xml
  def index
    @calendars = Calendar.find(:all)

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @calendars }
      format.json { render :json => @calendars }
    end
  end

  # GET /calendars/1
  # GET /calendars/1.xml
  def show
    @calendar = Calendar.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @calendar }
    end
  end

  # GET /calendars/new
  # GET /calendars/new.xml
  def new
    @calendar = Calendar.new

    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @calendar }
    end
  end

  # GET /calendars/1/edit
  def edit
    @calendar = Calendar.find(params[:id])
  end

  # POST /calendars
  # POST /calendars.xml
  def create
    @calendar = Calendar.new(params[:calendar])

    respond_to do |format|
      if @calendar.save
        flash[:notice] = 'Calendar was successfully created.'
        format.html { redirect_to(@calendar) }
        format.xml  { render :xml => @calendar, :status => :created, :location => @calendar }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @calendar.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /calendars/1
  # PUT /calendars/1.xml
  def update
    @calendar = Calendar.find(params[:id])

    respond_to do |format|
      if @calendar.update_attributes(params[:calendar])
        flash[:notice] = 'Calendar was successfully updated.'
        format.html { redirect_to(@calendar) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @calendar.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /calendars/1
  # DELETE /calendars/1.xml
  def destroy
    @calendar = Calendar.find(params[:id])
    @calendar.destroy

    respond_to do |format|
      format.html { redirect_to(calendars_url) }
      format.xml  { head :ok }
    end
  end
  
  private
  
  private
  
  def authenticate
    authenticate_or_request_with_http_basic do |user_name, password|
      @username = user_name
      not password.empty?
    end
  end
end
