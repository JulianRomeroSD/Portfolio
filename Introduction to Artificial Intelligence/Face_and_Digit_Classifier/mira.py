# mira.py
# -------
# Licensing Information: Please do not distribute or publish solutions to this
# project. You are free to use and extend these projects for educational
# purposes. The Pacman AI projects were developed at UC Berkeley, primarily by
# John DeNero (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# For more info, see http://inst.eecs.berkeley.edu/~cs188/sp09/pacman.html

# Mira implementation
import util
from random import uniform
PRINT = True

class MiraClassifier:
  """
  Mira classifier.
  
  Note that the variable 'datum' in this code refers to a counter of features
  (not to a raw samples.Datum).
  """
  def __init__( self, legalLabels, max_iterations):
    self.legalLabels = legalLabels
    self.type = "mira"
    self.automaticTuning = False 
    self.C = 0.001
    self.legalLabels = legalLabels
    self.max_iterations = max_iterations
    self.initializeWeightsToZero()

  def initializeWeightsToZero(self):
    "Resets the weights of each label to zero vectors" 
    self.weights = {}
    for label in self.legalLabels:
      self.weights[label] = util.Counter() # this is the data-structure you should use
  
  def train(self, trainingData, trainingLabels, validationData, validationLabels):
    "Outside shell to call your method. Do not modify this method."  
      
    self.features = trainingData[0].keys() # this could be useful for your code later...
    
    if (self.automaticTuning):
        Cgrid = [0.002, 0.004, 0.008]
    else:
        Cgrid = [self.C]
        
    return self.trainAndTune(trainingData, trainingLabels, validationData, validationLabels, Cgrid)

  def trainAndTune(self, trainingData, trainingLabels, validationData, validationLabels, Cgrid):
    """
    This method sets self.weights using MIRA.  Train the classifier for each value of C in Cgrid, 
    then store the weights that give the best accuracy on the validationData.
    
    Use the provided self.weights[label] data structure so that 
    the classify method works correctly. Also, recall that a
    datum is a counter from features to values for those features
    representing a vector of values.
    """
    "*** YOUR CODE HERE ***"
    #vectors = util.Counter()
    count = 0   # will be used to iterate throught trainingLabels
    myWeights = util.Counter()  # dict used to randomized weights

    """ 
    in this for loop you assign the key of myWeights to be the (x,y) 
    coordinates which corresponds to the key in each data and from there 
    you set a random weight between (-1 and 1) into myWeight[(x,y)]
    """
    for data in trainingData[0]:
      myWeights[data] = round(uniform(-1,1),3)

    """
    self.weights is a list of util.counters so self.weights[i]
    will hold the list of myWeights counters that correspond to that
    legalLabel for face ranges 0 or 1 digits 0-9
    """
    for i in self.legalLabels:
      self.weights[i] = myWeights

    """
    Here is where you take each image in trainingData and make a
    guess this for loop will call the myGuess function and determine
    if guess was correct if it was do nothing if wrong adjust the counters
    in to corresponding label by adding /subtracting the values in data which
    is also a util.Counter
    """
    for data in trainingData:
      guess = self.myGuess(data)
      truth = trainingLabels[count]
      print("Guessed : " + str(guess)+ "\tTruth : " + str(truth))
      if(guess == truth):
        print("guessed correctly")
      else:
        print("WRONG")
        #print(self.weights)
        self.weights[guess] = self.weights[guess].__sub__(data)
        self.weights[truth] = self.weights[truth].__add__(data)
        #print("AFTER")
        #print(self.weights)
      count += 1
      print count
    #util.raiseNotDefined()
  """
  helper function used to sum list of counters in each legal label * list of counters
  in each set of data from there that gives the total weight and assigns it to vectors 
  vectors[l] is a dict with the key being legalLabels and values being total weight
  prints vectors to see how weight are currently adjusted and then returns the legalLabel with 
  highest value.
  """
  def myGuess(self, data):
    vectors = util.Counter()
    for l in self.legalLabels:
      vectors[l] = self.weights[l] * data
      print(vectors)

    return vectors.argMax()

  def classify(self, data ):
    """
    Classifies each datum as the label that most closely matches the prototype vector
    for that label.  See the project description for details.
    
    Recall that a datum is a util.counter... 
    """
    guesses = []
    for datum in data:
      vectors = util.Counter()
      for l in self.legalLabels:
        vectors[l] = self.weights[l] * datum
      guesses.append(vectors.argMax())
    return guesses

  
  def findHighOddsFeatures(self, label1, label2):
    """
    Returns a list of the 100 features with the greatest difference in feature values
                     w_label1 - w_label2

    """
    featuresOdds = []

    "*** YOUR CODE HERE ***"

    return featuresOdds

