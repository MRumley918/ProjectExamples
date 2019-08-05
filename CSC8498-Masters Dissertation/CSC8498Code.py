import requests
import numpy as np
import matplotlib.pylab as plt
from datetime import datetime
from sklearn.preprocessing import MinMaxScaler
from scipy import interpolate
from math import floor, log10
#User input room number
print("Enter room number:")
roomNo = input()
print("Enter metric to compare")
metric = input()
#Use requests to get data
def get_values(metric):
    endpoint = 'https://api.usb.urbanobservatory.ac.uk//api/v2.0a/sensors'
    specificRoom = requests.get(endpoint+'/entity',params = {'meta:roomNumber':roomNo,'metric':metric}).json()
    tSeriesId = specificRoom['items'][0]['feed'][0]['timeseries'][0]['timeseriesId']
    values = requests.get(endpoint+'/timeseries/'+tSeriesId+'/historic').json()
    return values['historic']['values']
MetricTimes = []
MetricValues = []
#Populate arrays with data
for i in get_values(metric):
    MetricTimes.append(i['time'])
    MetricValues.append(i['value'])
#Stretch Occupancy data to fit properly
OccTimesFinal = []
OccValuesFinal = []
OccTimesFinal.append(MetricTimes[0])
for i in get_values("Occupancy Sensor"):
    OccTimesFinal.append(i['time'])
    OccTimesFinal.append(i['time'])
    if i['value']==0:
        OccValuesFinal.append(0)
        OccValuesFinal.append(1)
    elif i['value']==1:
        OccValuesFinal.append(1)
        OccValuesFinal.append(0)
OccTimesFinal.append(MetricTimes[-1])
firstOcc = OccValuesFinal[0]
lastOcc = OccValuesFinal[-1]
OccValuesFinal.insert(0,firstOcc)
OccValuesFinal.append(lastOcc)
#Normalisation of data
def normalise_array(Array):
    Array = np.reshape(Array,(-1,1))
    scaler = MinMaxScaler(feature_range=(0, 1))
    scaler = scaler.fit(Array)
    normalised = scaler.transform(Array)
    return normalised.ravel()
def normalise_time(TimesArray):
    intTimes = []
    for i in TimesArray:
        dateobj = datetime.strptime(i,'%Y-%m-%dT%H:%M:%S.%fZ')
        timeStamp = dateobj.timestamp()
        intTimes.append(timeStamp)
    return normalise_array(intTimes)
#Plot data on graph
fig,ax = plt.subplots(1)
ax_o = ax.twinx()
ax_o.plot(normalise_time(OccTimesFinal),OccValuesFinal,color='red')
ax.plot(normalise_time(MetricTimes),MetricValues)
starttime = datetime.strptime(MetricTimes[0],'%Y-%m-%dT%H:%M:%S.%fZ')
starttime = '{:%d %b %H:%M:%S}'.format(starttime)
endtime = datetime.strptime(MetricTimes[-1],'%Y-%m-%dT%H:%M:%S.%fZ')
endtime = '{:%d %b %H:%M:%S}'.format(endtime)
ax_o.set_xticks([0,1])
ax_o.set_xticklabels([endtime,starttime])
ax_o.set_yticks([0,1])
plt.title(roomNo+" "+metric+" data",loc='left')
#Interpolate Occupancy data to calculate correlation coefficient
OccX= normalise_time(OccTimesFinal)
OccY= normalise_array(OccValuesFinal)
f = interpolate.interp1d(OccX,OccY)
Xvalues = normalise_time(MetricTimes)
OccYInter = f(Xvalues)
correlation = np.corrcoef(OccYInter,normalise_array(MetricValues))[0,1]
correlation = round((correlation*100),1)
plt.title(str(correlation)+"% correlation",loc='right')

#Show Graph
fig.show()

