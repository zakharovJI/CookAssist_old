package com.example.moonside.cookassist;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AsyncTasks {
    private ProductDao productDao;
    createAsyncTask CAT;
    addAsyncTask AAT;
    deleteAsyncTask DAT;
    updateAsyncTask UAT;
    parseJSON PJS;


    public AsyncTasks(ProductDao productDao) {
        this.productDao = productDao;
    }

    static class insertAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDao mAsyncTaskDao;

        insertAsyncTask(ProductDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Product... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }

    }

    public void insert (Product product) {
        new insertAsyncTask(productDao).execute(product);
    }

    static class createAsyncTask extends AsyncTask<Void, Void, List<Product>> {

        private ProductDao mAsyncTaskDao;

        createAsyncTask(ProductDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Product> doInBackground(Void... voids) {
            List<Product> productList;
            productList = mAsyncTaskDao.getAll();
            return productList;
        }

        @Override
        protected void onPostExecute(List<Product> result) {
            super.onPostExecute(result);
        }

    }

    static class addAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDao mAsyncTaskDao;

        addAsyncTask(ProductDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Product... products) {
            mAsyncTaskDao.insert(products);
            Log.w("", "");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }

    }

    static class deleteAsyncTask extends AsyncTask<String , Void, Void> {

        private ProductDao mAsyncTaskDao;

        deleteAsyncTask(ProductDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... name) {
            mAsyncTaskDao.deleteByName(name);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }

    }

    static class updateAsyncTask extends AsyncTask<String, Void, Void> {

        private ProductDao mAsyncTaskDao;

        updateAsyncTask(ProductDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... strings) {
            mAsyncTaskDao.updateCount(strings[0], strings[2]);
            mAsyncTaskDao.updateCalories(strings[0], strings[3]);
            mAsyncTaskDao.updateName(strings[0], strings[1]);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }

    }

    static class parseJSON extends AsyncTask<String , Void, ArrayList<String[]>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<String[]> doInBackground(String... strings) {
            ArrayList<String[]> productJson = new ArrayList<String[]>();
            try {
                JSONObject obj = new JSONObject(strings[0]);
                JSONArray m_jArry = obj.getJSONArray("products");
                String[] m_li;

                for (int i = 0; i < m_jArry.length(); i++) {
                    JSONObject jo_inside = m_jArry.getJSONObject(i);
                    String product_name = jo_inside.getString("name");
                    String product_calories = jo_inside.getString("calories");

                    //Add your values in your `ArrayList` as below:
                    m_li = new String[2];
                    m_li[0] = product_name;
                    m_li[1] = product_calories;

                    productJson.add(m_li);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return productJson;
        }


        @Override
        protected void onPostExecute(ArrayList<String[]> result) {
            super.onPostExecute(result);
        }

    }


}
