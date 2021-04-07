package com.bhaktaprogram.coreapi

import com.bhaktaprogram.coreapi.navigation.MediatorsProvider
import com.bhaktaprogram.coreapi.repository.RepositoryProvider

interface ProvidersFacade : MediatorsProvider, RepositoryProvider, AppContextProvider