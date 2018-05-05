import numpy as np
from math import sqrt
from collections import Counter


class kNNClassifier:

    def __init__(self, k):
        """初始化kNN分类器"""
        assert k >= 1, "k must be valid"
        self.k = k
        self._X_train = None
        self._y_train = None

    def fit(self, X_train, y_train):
        """根据训练数据集X_train和y_train训练kNN分类器"""

        self._X_train = X_train
        self._y_train = y_train
        return self

    def predict(self, X_predict):
        """给定待预测数据集X_predict,返回表示X_predict的结果向量，注意X_predict一定要是二维矩阵哦"""
        assert self._X_train is not None and self._y_train is not None, "must fit before predict"
        assert X_predict.shape[1] == self._X_train.shape[1], "the feature number of  must be equal to X_train"  # 列数相等
        y_predict = [self._predict(x) for x in X_predict]
        return np.array(y_predict)

    def _predict(self, x):
        """给定单个待预测数据x，返回x_predict的预测结果"""
        # numpy数组的shape[1]表示数组的列数(在机器学习中，列一半作为特性，比如鸢尾花的花瓣的长、花瓣的宽、茎的高度、叶子形状等)
        assert self._X_train.shape[1] == x.shape[0], "the feature number of x must be equal to X_train"

        distances = [sqrt(np.sum((x_train - x) ** 2)) for x_train in self._X_train]
        # 获取最近的几个点
        nearest = np.argsort(distances)
        # 获取最近的几个点在目标向量中对应的目标值(即结果是哪个)
        topK_y = [self._y_train[i] for i in nearest[:self.k]]
        votes = Counter(topK_y)

        return votes.most_common(1)[0][0]

    def __repr__(self):
        return "kNN(k = %d)" % self.k
