<jsp:include page="/Chunks/links.jsp"></jsp:include>


<div class="dateFinder" style="padding-left: 30pt; padding-right: 30pt">
    <h1>Find dates near you!</h1>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="zip">Enter your ZIP code (ex: 28223):</label>
            <input type="text" class="form-control" id="zip">
        </div>
    </div>
    <div class="col-sm-8">
        <label>Select the range you'd like to search in: </label>
        <br>
        <label class="radio-inline"><input type="radio" name="optradio" checked>5 miles</label>
        <label class="radio-inline"><input type="radio" name="optradio">10 miles</label>
        <label class="radio-inline"><input type="radio" name="optradio">25 miles</label>
    </div>

</div>