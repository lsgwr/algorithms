import numpy as np

from .metrics import r2_score


class LinearRegression:

    def __init__(self):
        """初始化Linear Regression模型"""
        self.coef_ = None  # 系数矩阵，从_theta中截取
        self.interception_ = None  # 截距，从_theta中获取第一行
        self._theta = None  # 核心的参数，系数向量(包含截距)

    def fit_normal(self, X_train, y_train):
        """"""
        assert X_train.shape[0] == y_train.shape[0], " the size of X_train must be equal to the size of y_train"
        # 创建加上截距后的X_train--->X_b
        X_b = np.hstack([np.ones((len(X_train), 1)), X_train])
        # 参考本节推导出的公式
        self._theta = np.linalg.inv(X_b.T.dot(X_b)).dot(X_b.T).dot(y_train)
        self.interception_ = self._theta[0]
        self.coef_ = self._theta[1:]
        return self

    def predict(self, X_predict):
        """给定待测数据集X_predict,返回表示X_predict的结果向量"""
        assert self.interception_ is not None and self.coef_ is not None, " must fit before predict!"
        assert X_predict.shape[1] == len(self.coef_), "the feature of X_predict must be equal to X_train"
        X_b = np.hstack([np.ones((len(X_predict), 1)), X_predict])
        # 点乘即得到预测结果
        return X_b.dot(self._theta)

    def score(self, X_test, y_test):
        y_predict = self.predict(X_test)
        return r2_score(y_test, y_predict)

    def __repr__(self):
        return "LinearRegression()"
